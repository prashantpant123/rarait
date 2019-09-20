package com.rarait.education.structure.level.impl;

import com.rarait.education.structure.level.SectionService;
import com.rarait.education.structure.level.resource.SectionCreateRequest;
import com.rarait.education.structure.level.resource.SectionDetailResource;
import com.rarait.education.structure.level.resource.SectionResource;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.shared.route.InstituteRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@RestController
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = InstituteRoute.SECTION)
    public void addSection(@RequestBody SectionCreateRequest request) {
        sectionService.createSection(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = InstituteRoute.SECTION_ID)
    public SectionDetailResource getSectionInfo(@PathVariable("section_id") int sectionId) {
        return sectionService.findOneInfo(sectionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.SECTION)
    public PagedResponse<SectionResource> findAllSection(@RequestParam("page") int pageNumber,
                                                         @RequestParam("class_id") int levelId) {
        return sectionService.findAllSection(levelId, pageNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.SECTION_LIST)
    public List<DropdownListResource> findList(@PathVariable("class_id") int leveId) {
        return sectionService.findAllForList(leveId);
    }
}
