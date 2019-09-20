package com.rarait.education.institute.impl;

import com.rarait.education.institute.InstituteService;
import com.rarait.education.institute.resource.InstituteBasicInfo;
import com.rarait.education.shared.route.InstituteRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class InstituteController {

    private final InstituteService instituteService;

    public InstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GetMapping(InstituteRoute.BASIC_INFO)
    @ResponseStatus(HttpStatus.OK)
    public InstituteBasicInfo getBasicDetail() {
        return instituteService.findBasicInfo();
    }
}
