package com.rarait.education.structure.level;

import com.rarait.education.structure.level.resource.SectionCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface SectionValidator {
    void validate(SectionCreateRequest request);
}
