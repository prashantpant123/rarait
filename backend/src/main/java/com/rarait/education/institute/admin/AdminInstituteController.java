package com.rarait.education.institute.admin;

import com.rarait.education.institute.InstituteService;
import com.rarait.education.institute.InstituteType;
import com.rarait.education.institute.resource.*;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.route.AdminRoute;
import com.rarait.education.student.resource.RegistrationCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class AdminInstituteController {

    private final InstituteService instituteService;

    @Autowired
    public AdminInstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = AdminRoute.INSTITUTE)
    public InstituteCreateResponse addInstitute(@RequestBody InstituteCreateRequest request) {
        return instituteService.addInstitute(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = AdminRoute.INSTITUTE_DETAIL)
    public void updateSchoolDetail(
            @PathVariable("institute_id") Integer instituteId,
            @RequestBody InstituteCreateRequest request) {
        instituteService.updateInstituteDetail(instituteId, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = AdminRoute.INSTITUTE_EDIT)
    public void updateInstitute(@RequestBody InstituteUpdateResource request) {
        instituteService.updateInstitute(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = AdminRoute.INSTITUTE_STATUS)
    public void updateInstituteStatus(@RequestBody InstituteStatusResource request) {
        instituteService.updateInstituteStatus(request);
    }

    @GetMapping(value = AdminRoute.INSTITUTE)
    @ResponseStatus(HttpStatus.OK)
    public PagedResponse<InstituteResponse> getAllActive(@RequestParam("page") Integer page) {
        return instituteService.findAllActive(page);
    }

    @GetMapping(value = AdminRoute.INSTITUTE_DETAIL)
    @ResponseStatus(HttpStatus.OK)
    public InstituteDetailResource getSchoolDetail(@PathVariable("institute_id") Integer instituteId) {
        return instituteService.findInstituteDetail(instituteId);
    }


    @GetMapping(value = AdminRoute.INSTITUTE_TYPE)
    @ResponseStatus(HttpStatus.OK)
    public List<InstituteTypeResource> getAllInstituteType() {
        List<InstituteType> it = new ArrayList<>(EnumSet.allOf(InstituteType.class));
        return it.stream()
                .map(i -> new InstituteTypeResource(i.toString()))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = AdminRoute.INSTITUTE_REG_ID_CHECK)
    public RegistrationCheckResponse checkInstituteRegistrationId(@PathVariable("registration_id") String registrationId) {
        return RegistrationCheckResponse.builder()
                .exist(instituteService.checkInstituteRegistrationPrefix(registrationId))
                .registrationNumber(registrationId)
                .build();
    }
}
