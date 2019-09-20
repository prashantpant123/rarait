package com.rarait.education.login;

import com.rarait.framework.security.resource.ChangePasswordRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface ChangePasswordValidator {

    void validate(ChangePasswordRequest request);
}
