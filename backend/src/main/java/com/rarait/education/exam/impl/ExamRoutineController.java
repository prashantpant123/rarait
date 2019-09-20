package com.rarait.education.exam.impl;

import com.rarait.education.exam.ExamRoutineService;
import com.rarait.education.exam.resource.ExamRoutineCreateList;
import com.rarait.education.exam.resource.ExamRoutineCreateRequest;
import com.rarait.education.exam.resource.ExamRoutineResource;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class ExamRoutineController {

    private final ExamRoutineService examRoutineService;

    public ExamRoutineController(ExamRoutineService examRoutineService) {
        this.examRoutineService = examRoutineService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.EXAM_ROUTINE)
    public void addExamRoutine(@RequestBody ExamRoutineCreateList request) {
        log.info("Request: {}",request.toString());
        examRoutineService.addExamRoutines(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EXAM_ROUTINE)
    public PagedResponse<ExamRoutineResource> getExamRoutineList(
            @RequestParam("exam_id") int examId,
            @RequestParam("class_id") int classId) {
        return examRoutineService.findAllExamRoutine(examId, classId);
    }
}
