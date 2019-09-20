package com.rarait.education.staff.impl;

import com.rarait.education.document.Document;
import com.rarait.education.document.DocumentService;
import com.rarait.education.document.DocumentType;
import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.*;
import com.rarait.education.staff.EmployeeRepository;
import com.rarait.education.staff.EmployeeService;
import com.rarait.education.staff.EmployeeValidator;
import com.rarait.education.staff.resource.*;
import com.rarait.education.summary.SummaryService;
import com.rarait.education.utility.bloodgroup.BloodGroup;
import com.rarait.education.utility.nationality.NationalityService;
import com.rarait.framework.date.CalendarConversion;
import com.rarait.framework.exception.ClientRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final InstituteLoginSession instituteLoginSession;
    private final EmployeeValidator employeeValidator;
    private final SummaryService summaryService;
    private final DocumentService documentService;
    private final NationalityService nationalityService;

    @Value(AppProperties.DOMAIN_URL)
    private String domain;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               InstituteLoginSession instituteLoginSession,
                               EmployeeValidator employeeValidator,
                               SummaryService summaryService,
                               DocumentService documentService,
                               NationalityService nationalityService) {
        this.employeeRepository = employeeRepository;
        this.instituteLoginSession = instituteLoginSession;
        this.employeeValidator = employeeValidator;
        this.summaryService = summaryService;
        this.documentService = documentService;
        this.nationalityService = nationalityService;
    }

    @Override
    public List<EmployeeTypeDropdownResource> findAllEmployeeType() {
        return EmployeeConvert.convertTypeList();
    }

    @Override
    @Transactional
    public EmployeeCreateResponse addEmployee(EmployeeCreateRequest request) {
        employeeValidator.validate(request);
        Institute institute = instituteLoginSession.getCurrentInstitute();

        Employee emp = new Employee();
        emp.setEmployeeId(request.getEmployeeId());
        emp.setFirstName(request.getFirstName());
        emp.setLastName(request.getLastName());
        emp.setGender(Gender.valueOf(request.getGenderId()).get());
        emp.setJoiningDate(request.getJoiningDate());
        emp.setInstitute(institute);
        emp.setType(EmployeeType.valueOf(request.getTypeId()).get());
        emp.setPrimaryContact(request.getPrimaryContact());
        emp.setSecondaryContact(request.getSecondaryContact());
        emp.setCurrentAddress(request.getCurrentAddress());
        emp.setPermanentAddress(request.getPermanentAddress());
        emp.setQualification(request.getQualification());
        emp.setDesignation(request.getDesignation());

        emp.setDobAd(request.getDateOfBirth());
        emp.setDobBs(CalendarConversion.adToBs(request.getDateOfBirth()));
        emp.setBloodGroup(BloodGroup.getEnumFromOrdinal(request.getBloodGroupId()));
        emp.setExperienceSummary(request.getExperienceSummary());
        emp.setMaritalStatus(MaritalStatus.getEnum(request.getMaritalStatus()));
        emp.setNationality(nationalityService.findOneById(request.getNationalityId()));
        emp = employeeRepository.save(emp);

        summaryService.updateTeacher();
        return EmployeeCreateResponse.builder()
                .id(emp.getId())
                .message("Employee registered successfully")
                .build();
    }

    @Override
    @Transactional
    public PagedResponse<EmployeeListResponse> findAllEmployeeWithFilter(int pageNumber, int typeId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        PageRequest pageRequest = PagedRequest.get(pageNumber, Sort.by("id").descending());
        Page<Employee> employees = null;
        if (typeId > 0) {
            employees = employeeRepository.findAllByInstituteAndType(institute.getId(),
                    EmployeeType.valueOf(typeId).get(),
                    pageRequest);
        } else {
            employees = employeeRepository.findAllByInstitute(institute.getId(),
                    pageRequest);
        }
        return new PagedResponse<>(employees.getTotalElements(),
                employees.getTotalPages(),
                pageNumber,
                EmployeeConvert.convertToList(employees.getContent()));
    }

    @Override
    public EmployeeDetailResource getEmployeeDetail(long employeeId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Employee employee = getOneById(employeeId, institute.getId());
        List<Document> documents = documentService.getDocumentForInstitute(employeeId, DocumentType.STAFF_PICTURE, institute.getId());
        return EmployeeConvert.convertDetail(employee, documents == null || documents.isEmpty() ? null : documents.get(0), domain);
    }

    private Employee getOneById(long employeeId, int instituteId) {
        Optional<Employee> employees = employeeRepository.findOneByInstituteAndId(instituteId, employeeId);
        return employees.orElseThrow(() -> new ClientRestException("Employee not found"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadDocument(MultipartFile file, DocumentType type, Long employeeId) {
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Employee employee = getOneById(employeeId, institute.getId());
        documentService.createDocumentForInstitute(file, type, employee.getId(), institute);
    }

}
