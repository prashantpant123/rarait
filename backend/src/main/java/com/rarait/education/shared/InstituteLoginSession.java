package com.rarait.education.shared;

import com.rarait.education.exception.InvalidSessionException;
import com.rarait.education.institute.InstituteLoginService;
import com.rarait.education.institute.impl.Institute;
import com.rarait.framework.security.auth.AuthSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
public class InstituteLoginSession {

    private final InstituteLoginService instituteLoginService;

    @Autowired
    public InstituteLoginSession(InstituteLoginService instituteLoginService) {
        this.instituteLoginService = instituteLoginService;
    }

    public Institute getCurrentInstitute() {
        long userLoginId = AuthSessionUtil.getCurrentUser().getUser().getId();
        return instituteLoginService.findInstituteLoginById(userLoginId);
    }
}
