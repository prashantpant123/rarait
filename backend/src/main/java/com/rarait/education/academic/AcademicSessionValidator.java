package com.rarait.education.academic;

import com.rarait.education.academic.resource.AcademicSessionCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface AcademicSessionValidator {
    void validate(AcademicSessionCreateRequest request);
}
