package com.rarait.education.structure.level;

import com.rarait.education.structure.level.resource.LevelCreateRequest;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface LevelValidator {
    void validate(LevelCreateRequest request);
}
