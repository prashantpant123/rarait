package com.rarait.education.utility.occupation.impl;

import com.rarait.education.shared.route.UtilityRoute;
import com.rarait.education.utility.occupation.spi.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class OccupationController {

    private final OccupationService occupationService;

    @Autowired
    public OccupationController(OccupationService occupationService) {
        this.occupationService = occupationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(UtilityRoute.OCCUPATION)
    public List<OccupationResource> getAllOccupationList() {
        return occupationService.findAll();
    }
}
