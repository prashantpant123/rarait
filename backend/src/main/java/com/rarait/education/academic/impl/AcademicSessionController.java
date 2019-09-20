package com.rarait.education.academic.impl;

import com.rarait.education.academic.AcademicSessionService;
import com.rarait.education.academic.resource.AcademicSessionCreateRequest;
import com.rarait.education.academic.resource.AcademicSessionDropdownResource;
import com.rarait.education.academic.resource.AcademicSessionResource;
import com.rarait.education.shared.PagedRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.InstituteRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class AcademicSessionController {

    private final AcademicSessionService academicSessionService;

    @Autowired
    public AcademicSessionController(AcademicSessionService academicSessionService) {
        this.academicSessionService = academicSessionService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(InstituteRoute.ACADEMIC_SESSION)
    public void createAcademicSession(@RequestBody AcademicSessionCreateRequest request) {
        academicSessionService.createAcademicSession(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.ACADEMIC_SESSION)
    public PagedResponse<AcademicSessionResource> getAllAcademicSession(
            @RequestParam("page") Integer page,
            @RequestParam(value = "sort_field", required = false) String sortField,
            @RequestParam(value = "ascend", required = false) Boolean ascend,
            @RequestParam(value = "session", required = false) Boolean... session) {
        return academicSessionService.getAllAcademicSession(page, sortField, ascend, session);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(InstituteRoute.ACADEMIC_SESSION_EDIT)
    public void updateAcademicSession() {

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.ACADEMIC_SESSION_ID)
    public AcademicSessionResource getSessionInfo(@PathVariable("academic_session_id") Integer academicSessionId) {
        return academicSessionService.findDetailForId(academicSessionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.ACADEMIC_SESSION_LIST)
    public List<AcademicSessionDropdownResource> getAllSession() {
        return academicSessionService.getAllAcademicSessionDropdownResources();
    }
}
