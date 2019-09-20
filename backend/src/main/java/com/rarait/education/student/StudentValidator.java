package com.rarait.education.student;

import com.rarait.education.student.resource.StudentRegisterRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface StudentValidator {
    void validate(StudentRegisterRequest request);
}
