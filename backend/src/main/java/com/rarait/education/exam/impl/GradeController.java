package com.rarait.education.exam.impl;

import com.rarait.education.exam.GradeService;
import com.rarait.education.exam.resource.GradeCreateRequest;
import com.rarait.education.exam.resource.GradeDetailResource;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.GRADE_ID)
    public GradeDetailResource getDetail(@PathVariable("grade_id") int gradeId) {
        return gradeService.findDetailForId(gradeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.GRADE)
    public void createGrade(@RequestBody GradeCreateRequest request) {
        gradeService.createGrade(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.GRADE)
    public PagedResponse<GradeDetailResource> getAllGrades(@RequestParam("page") int pageNumber) {
        return gradeService.findAllGrade(pageNumber);
    }
}
