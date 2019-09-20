package com.rarait.education.institute;

import com.rarait.education.institute.resource.InstituteCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface InstituteValidator {
    void validate(InstituteCreateRequest request);
}
