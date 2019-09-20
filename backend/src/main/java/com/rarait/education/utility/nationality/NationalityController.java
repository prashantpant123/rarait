package com.rarait.education.utility.nationality;

import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.shared.route.UtilityRoute;
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
public class NationalityController {

    private final NationalityService nationalityService;

    public NationalityController(NationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(UtilityRoute.NATIONALITY)
    public List<DropdownListResource> getAllNationality() {
        return nationalityService.findAllNationality();
    }
}
