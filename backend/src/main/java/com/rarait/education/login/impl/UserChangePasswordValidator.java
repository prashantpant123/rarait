package com.rarait.education.login.impl;

import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.security.resource.ChangePasswordRequest;
import com.rarait.framework.shared.InputUtil;
import com.rarait.education.login.ChangePasswordValidator;
import com.rarait.education.utility.properties.ApplicationPropertyService;
import com.rarait.education.utility.properties.PropertyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
public class UserChangePasswordValidator implements ChangePasswordValidator {

    private final ApplicationPropertyService applicationPropertyService;

    @Autowired
    public UserChangePasswordValidator(ApplicationPropertyService applicationPropertyService) {
        this.applicationPropertyService = applicationPropertyService;
    }

    @Override
    public void validate(ChangePasswordRequest request) {
        if (InputUtil.isEmpty(request.getPassword())) {
            throw new ClientRestException("Password must not be empty or null");
        } else if (InputUtil.isEmpty(request.getConfirmPassword())) {
            throw new ClientRestException("Confirm password must not be empty or null");
        } else if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new ClientRestException("Confirm password does not match.");
        } else {
            String passwordRegex = applicationPropertyService.getActiveValueByType(PropertyType.PASSWORD_REGEX);
            if (!String.valueOf(request.getPassword()).matches(passwordRegex)) {
                throw new ClientRestException("Invalid password format!");
            }
        }
    }
}