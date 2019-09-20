package com.rarait.education.institute.impl;

import com.rarait.education.document.DocumentService;
import com.rarait.education.document.DocumentType;
import com.rarait.education.institute.*;
import com.rarait.education.institute.resource.*;
import com.rarait.education.login.InstituteUserService;
import com.rarait.education.login.impl.RoleName;
import com.rarait.education.login.impl.User;
import com.rarait.education.shared.AppProperties;
import com.rarait.education.shared.InstituteLoginSession;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.AdminRoute;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.utility.BaseConversion;
import com.rarait.education.utility.address.spi.AddressService;
import com.rarait.framework.exception.ClientRestException;

import com.rarait.framework.security.auth.AuthSessionUtil;
import com.rarait.framework.shared.InputUtil;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class InstituteServiceImpl implements InstituteService {

    private final InstituteRepository instituteRepository;
    private final ContactPersonRepository contactPersonRepository;
    private final InstituteValidator instituteValidator;
    private final InstituteLoginService instituteLoginService;
    private final InstituteUserService instituteUserService;
    private final InstituteLoginSession instituteLoginSession;
    private final DocumentService documentService;

    @Value(AppProperties.DOMAIN_URL)
    private String domain;

    @Autowired
    public InstituteServiceImpl(InstituteRepository instituteRepository,
                                ContactPersonRepository contactPersonRepository,
                                InstituteValidator instituteValidator,
                                InstituteLoginService instituteLoginService,
                                InstituteUserService instituteUserService,
                                InstituteLoginSession instituteLoginSession,
                                DocumentService documentService) {
        this.instituteRepository = instituteRepository;
        this.contactPersonRepository = contactPersonRepository;
        this.instituteValidator = instituteValidator;
        this.instituteLoginService = instituteLoginService;
        this.instituteUserService = instituteUserService;
        this.instituteLoginSession = instituteLoginSession;
        this.documentService = documentService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public InstituteCreateResponse addInstitute(InstituteCreateRequest request) {
        instituteValidator.validate(request);
        request.getUser().setRoleNames(Arrays.asList(RoleName.ROLE_INSTITUTE_ADMIN));
        User instituteAdmin = instituteUserService.addCredential(request.getUser());

        Institute institute = new Institute();
        institute.setName(request.getName());
        institute.setAddress(request.getAddress());
        institute.setPrincipal(request.getPrincipal());
        institute.setLandline(request.getLandline());
        institute.setWebsite(request.getWebsite());
        institute.setRegistrationPrefix(request.getRegistrationNoPrefix());

        if (request.getContact() != null &&
                !InputUtil.isEmpty(request.getContact().getFullname())) {
            ContactPerson cp = new ContactPerson();
            cp.setDesignation(request.getContact().getDesignation());
            cp.setFullname(request.getContact().getFullname());
            cp.setMobileNumber(request.getContact().getMobileNumber());
            cp.setLandline(request.getContact().getLandlineNumber());
            cp.setEmailId(request.getContact().getEmailId());
            cp = contactPersonRepository.save(cp);
            institute.setContact(new ArrayList<>());
            institute.getContact().add(cp);
        }
        institute = instituteRepository.save(institute);

        instituteLoginService.addInstituteLogin(institute, instituteAdmin);
        return InstituteCreateResponse.builder()
                .id(institute.getId())
                .message("Education institute created successfully")
                .build();
    }

    @Override
    public void updateInstitute(InstituteUpdateResource request) {
        instituteValidator.validate(request);

        Institute institute = findOneById(request.getId());
        institute.setName(request.getName());
        institute.setAddress(request.getAddress());
        instituteRepository.save(institute);
    }

    @Override
    public void updateInstituteStatus(InstituteStatusResource request) {
        Institute institute = findOneById(request.getId());
        institute.setStatus(Status.valueOf(request.getStatus()));
        institute.setRemarks(request.getRemarks());
        instituteRepository.save(institute);
    }

    @Override
    public PagedResponse<InstituteResponse> findAllActive(int pageNumber) {
        Page<Institute> institutes = instituteRepository.findAllInstituteByStatus(Status.ACTIVE,
                PagedRequest.get(pageNumber, Sort.by("name")));

        return new PagedResponse<>(
                institutes.getTotalElements(),
                institutes.getTotalPages(),
                pageNumber,
                InstituteConverter.convertToList(institutes.getContent()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public InstituteDetailResource findInstituteDetail(int instituteId) {
        Institute institute = findOneById(instituteId);

        List<InstituteLoginResource> ilr = instituteLoginService.findAllForInstitute(institute.getId());
        List<ContactPersonResource> cpr = InstituteConverter.convertContactPersonList(institute.getContact());
        return InstituteConverter.convertDetail(institute, ilr, cpr, domain);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInstituteDetail(int instituteId, InstituteCreateRequest request) {
        log.info(request.toString());
        instituteValidator.validate(request);

        Institute institute = findOneById(instituteId);
        institute.setName(request.getName());
        institute.setLandline(request.getLandline());
        instituteRepository.save(institute);
    }

    @Override
    public Institute findOneById(int instituteId) {
        Optional<Institute> ins = instituteRepository.findById(instituteId);
        return ins.orElseThrow(() -> new ClientRestException("Institute not found!"));
    }

    @Override
    public Institute findByLoginId(long loginId) {
        return instituteLoginService.findInstituteByLoginId(loginId);
    }

    @Override
    public InstituteBasicInfo findBasicInfo() {
        long userLoginId = AuthSessionUtil.getCurrentUser().getUser().getId();
        Institute institute = instituteLoginService.findInstituteLoginById(userLoginId);
        return InstituteBasicInfo.builder()
                .name(institute.getName())
                .username(AuthSessionUtil.getCurrentUser().getUsername())
                .lastLogin(AuthSessionUtil.getCurrentUser().getUser().getLastLogin().toString())
                .landline(institute.getLandline())
                .address(institute.getAddress())
                .logo(domain + InstituteRoute.INSTITUTE + "/" + BaseConversion.getCode(institute.getId()) + "/file/" + institute.getFilename())
                .registrationNoPrefix(institute.getRegistrationPrefix())
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadDocumentForAdmin(MultipartFile file, DocumentType type, Integer instituteId) {
        Institute institute = findOneById(instituteId);
        String filename = documentService.createDocumentForAdmin(file, type, institute);
        institute.setFilename(filename);
        instituteRepository.save(institute);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadDocumentForInstitute(MultipartFile file, DocumentType type, Long userId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        documentService.createDocumentForInstitute(file, type, userId, institute);
    }

    @Override
    public Resource getDocument(String fileName, Long userId, int instituteId) {
        return documentService.downloadFile(fileName, userId, instituteId);
    }

    @Override
    public Resource getDocument(String fileName, int instituteId) {
        return documentService.downloadFile(fileName, instituteId);
    }

    @Override
    public boolean checkInstituteRegistrationPrefix(String registrationPrefix) {
        if (InputUtil.isEmpty(registrationPrefix)) {
            throw new ClientRestException("Registration prefix is missing or empty");
        }
        Optional<Institute> institute = instituteRepository.findOneByRegistrationPrefix(registrationPrefix);
        return institute.isPresent();
    }
}
