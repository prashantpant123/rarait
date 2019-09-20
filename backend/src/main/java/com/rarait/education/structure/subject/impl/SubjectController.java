package com.rarait.education.structure.subject.impl;

import com.rarait.education.structure.resource.SubjectCreateRequest;
import com.rarait.education.structure.resource.SubjectResource;
import com.rarait.education.structure.resource.SubjectUpdateRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.structure.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.SUBJECT)
    public void addSubject(@RequestBody SubjectCreateRequest request) {
        subjectService.addSubject(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.SUBJECT_UPDATE)
    public void updateSubject(@RequestBody SubjectUpdateRequest request) {
        subjectService.updateSubject(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.SUBJECT_ID)
    public SubjectResource getSubjectDetail(@PathVariable("subject_id") int subjectId) {
        return subjectService.findSubjectById(subjectId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.SUBJECT)
    public PagedResponse<SubjectResource> getAllSubject(
            @RequestParam("page") int page,
            @RequestParam(value = "class_id", required = false) Integer levelId,
            @RequestParam(value = "sort_field", required = false) String sortField,
            @RequestParam(value = "ascend", required = false) Boolean ascend) {
        return subjectService.findAllSubjects(page, levelId, sortField, ascend);
    }
}