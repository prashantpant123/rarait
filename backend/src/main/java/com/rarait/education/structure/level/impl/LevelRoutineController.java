package com.rarait.education.structure.level.impl;

import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.structure.level.LevelRoutineService;
import com.rarait.education.structure.level.resource.LevelRoutineCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class LevelRoutineController {

    private final LevelRoutineService levelRoutineService;

    public LevelRoutineController(LevelRoutineService levelRoutineService) {
        this.levelRoutineService = levelRoutineService;
    }

    @PostMapping(InstituteRoute.LEVEL_ROUTINE)
    public void createRoutine(@RequestBody List<LevelRoutineCreateRequest> requests) {
        levelRoutineService.createRoutine(requests);
    }
}
