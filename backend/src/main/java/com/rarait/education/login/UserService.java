package com.rarait.education.login;

import com.rarait.education.login.impl.User;
import com.rarait.framework.security.resource.ChangePasswordRequest;
import com.rarait.framework.security.resource.RegistrationValidationRequest;
import com.rarait.education.login.resource.UserCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface UserService {

    User findByUsername(String username);

    User addCredential(UserCreateRequest request);

    User verifyRegistration(RegistrationValidationRequest request);

    void changePassword(ChangePasswordRequest request);
}
