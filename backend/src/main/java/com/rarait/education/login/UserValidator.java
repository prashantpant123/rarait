package com.rarait.education.login;

import com.rarait.education.login.resource.UserCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface UserValidator {
    void validate(UserCreateRequest request);
}
