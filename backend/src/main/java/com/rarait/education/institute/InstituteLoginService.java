package com.rarait.education.institute;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.institute.impl.InstituteLogin;
import com.rarait.education.institute.resource.InstituteLoginResource;
import com.rarait.education.login.impl.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface InstituteLoginService {

    void addInstituteLogin(Institute institute, User login);

    void deactivateInstituteLogin(int instituteId, int instituteLoginId);

    void removeInstituteLogin(int instituteId, int instituteLoginId);

    Institute findInstituteByLoginId(long loginId);

    List<InstituteLoginResource> findAllForInstitute(int instituteId);

    Institute findInstituteLoginById(long loginId);
}
