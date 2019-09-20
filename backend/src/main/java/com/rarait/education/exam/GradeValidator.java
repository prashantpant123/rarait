package com.rarait.education.exam;

import com.rarait.education.exam.resource.GradeCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface GradeValidator {

    void validate(GradeCreateRequest request);
}
