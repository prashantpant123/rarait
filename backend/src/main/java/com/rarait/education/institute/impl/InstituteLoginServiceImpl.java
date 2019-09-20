package com.rarait.education.institute.impl;

import com.rarait.education.exception.InvalidSessionException;
import com.rarait.education.institute.InstituteLoginService;
import com.rarait.education.institute.resource.InstituteLoginResource;
import com.rarait.education.login.impl.User;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class InstituteLoginServiceImpl implements InstituteLoginService {

    private InstituteLoginRepository instituteLoginRepository;

    @Autowired
    public InstituteLoginServiceImpl(InstituteLoginRepository instituteLoginRepository) {
        this.instituteLoginRepository = instituteLoginRepository;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addInstituteLogin(Institute institute, User login) {
        InstituteLogin instituteLogin = new InstituteLogin();
        instituteLogin.setInstitute(institute);
        instituteLogin.setStatus(Status.ACTIVE);
        instituteLogin.setUser(login);
        instituteLoginRepository.save(instituteLogin);
    }

    @Override
    public void deactivateInstituteLogin(int instituteId, int instituteLoginId) {
        InstituteLogin instituteLogin = instituteLoginRepository.findOneByInstituteAndId(instituteId, instituteLoginId);
        if (instituteLogin == null || !instituteLogin.getStatus().equals(Status.ACTIVE)) {
            throw new ClientRestException("Request failed, no such details found!");
        }
        instituteLogin.setStatus(Status.INACTIVE);
        instituteLoginRepository.save(instituteLogin);
    }

    @Override
    public void removeInstituteLogin(int instituteId, int instituteLoginId) {
        InstituteLogin instituteLogin = instituteLoginRepository.findOneByInstituteAndId(instituteId, instituteLoginId);
        if (instituteLogin == null || !instituteLogin.getStatus().equals(Status.DELETED)) {
            throw new ClientRestException("Request failed, no such details found!");
        }
        instituteLogin.setStatus(Status.DELETED);
        instituteLoginRepository.save(instituteLogin);
    }

    @Override
    public Institute findInstituteByLoginId(long loginId) {
        return instituteLoginRepository.findByLoginId(loginId, Status.ACTIVE);
    }

    @Override
    public List<InstituteLoginResource> findAllForInstitute(int instituteId) {
        return InstituteConverter.convertLoginList(instituteLoginRepository.findAllByInstitute(instituteId));
    }

    @Override
    public Institute findInstituteLoginById(long loginId) {
        Optional<Institute> ins= Optional.of(findInstituteByLoginId(loginId));
        return ins.orElseThrow(() -> new ClientRestException("Not found"));
    }
}
