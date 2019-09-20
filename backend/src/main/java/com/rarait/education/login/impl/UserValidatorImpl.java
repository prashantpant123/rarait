package com.rarait.education.login.impl;

import com.rarait.education.login.UserValidator;
import com.rarait.education.login.resource.UserCreateRequest;
import com.rarait.education.utility.properties.ApplicationPropertyService;
import com.rarait.education.utility.properties.PropertyType;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.InputUtil;
import com.rarait.framework.shared.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Component
public class UserValidatorImpl implements UserValidator {

    private final ApplicationPropertyService applicationPropertyService;

    @Autowired
    public UserValidatorImpl(ApplicationPropertyService applicationPropertyService) {
        this.applicationPropertyService = applicationPropertyService;
    }

    @Override
    public void validate(UserCreateRequest request) {
        if (InputUtil.isEmpty(request.getUsername())) {
            throw new ClientRestException("Username cannot be empty or null");
        } else if (InputUtil.isEmpty(request.getPassword())) {
            throw new ClientRestException("Password cannot be empty or null");
        } else if (!RegexUtil.isValidUsername(request.getUsername())) {
            throw new ClientRestException("Username must be valid mobile numberPlate or email id. " +
                    "E.g: 98######## or education@email.com.np");
        } else if (request.getRoleNames() == null) {
            throw new ClientRestException("User role not found.");
        } else {
            String passwordRegex = applicationPropertyService.getActiveValueByType(PropertyType.PASSWORD_REGEX);
            if (!InputUtil.isEmpty(passwordRegex) &&
                    !String.valueOf(request.getPassword()).matches(passwordRegex)) {
                throw new ClientRestException("Invalid password format!");
            }
        }
    }
}