package com.rarait.education.structure.level;

import com.rarait.education.structure.level.resource.LevelRoutineCreateRequest;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface LevelRoutineService {

    void createRoutine(List<LevelRoutineCreateRequest> requests);
}
