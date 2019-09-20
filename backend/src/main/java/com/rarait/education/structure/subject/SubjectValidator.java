package com.rarait.education.structure.subject;

import com.rarait.education.structure.resource.SubjectCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface SubjectValidator {
    void validate(SubjectCreateRequest request);
}
