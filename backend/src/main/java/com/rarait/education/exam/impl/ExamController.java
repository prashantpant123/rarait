package com.rarait.education.exam.impl;

import com.rarait.education.exam.ExamTermService;
import com.rarait.education.exam.ExamService;
import com.rarait.education.exam.resource.*;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.shared.route.InstituteRoute;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class ExamController {

    private final ExamService examService;
    private final ExamTermService examTermService;

    public ExamController(ExamService examService,
                          ExamTermService examTermService) {
        this.examService = examService;
        this.examTermService = examTermService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.EXAM)
    public void addExam(@RequestBody ExamCreateRequest request) {
        examService.addExam(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EXAM_ID)
    public ExamDetailResource getExamInfo(@PathVariable("exam_id") Integer examId) {
        return examService.getExamDetailId(examId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EXAM)
    public PagedResponse<ExamResource> getExamList(
            @RequestParam("page") Integer pageNumber) {
        return examService.findAllExam(pageNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EXAM_LIST)
    public List<ExamDropdownResource> getAllExamList() {
        return examService.findAllExamList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EXAM_SESSION_LIST)
    public List<DropdownListResource> getAllExamSessionList() {
        return examService.findExamSessionList();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.EXAM_TERM)
    public void addExamTerm(@RequestBody ExamCategoryCreateRequest request) {
        examTermService.createTerm(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EXAM_TERM)
    public PagedResponse<ExamCategoryResource> getExamTerm(@RequestParam("page") Integer pageNumber) {
        return examTermService.findAllActiveByInstitute(pageNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.EXAM_TERM_LIST)
    public List<DropdownListResource> getAllExamTermList() {
        return examTermService.findExamTermList();
    }
}
