package com.rarait.education.utility.occupation.impl;

import com.rarait.education.shared.route.AdminRoute;
import com.rarait.education.utility.occupation.spi.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class AdminOccupationController {

    private final OccupationService occupationService;

    @Autowired
    public AdminOccupationController(OccupationService occupationService) {
        this.occupationService = occupationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(AdminRoute.OCCUPATION)
    public void addOccupation(@RequestBody OccupationCreateRequest request) {
        occupationService.addOccupation(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(AdminRoute.OCCUPATION_EDIT)
    public void updateOccupation(@RequestBody OccupationUpdateRequest request) {
        occupationService.updateOccupation(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(AdminRoute.OCCUPATION)
    public List<OccupationResource> getAllOccupation() {
        return occupationService.findAll();
    }
}