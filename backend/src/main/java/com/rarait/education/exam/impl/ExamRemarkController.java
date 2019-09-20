package com.rarait.education.exam.impl;

import com.rarait.education.exam.ExamRemarkService;
import com.rarait.education.exam.resource.ExamRemarkCreateRequest;
import com.rarait.education.shared.route.InstituteRoute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class ExamRemarkController {

    private final ExamRemarkService examRemarkService;

    public ExamRemarkController(ExamRemarkService examRemarkService) {
        this.examRemarkService = examRemarkService;
    }

    @PostMapping(InstituteRoute.EXAM_REMARK)
    public void createRemark(@RequestBody ExamRemarkCreateRequest request) {
        examRemarkService.createRemark(request);
    }
}
