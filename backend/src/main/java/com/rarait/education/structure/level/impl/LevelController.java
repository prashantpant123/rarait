package com.rarait.education.structure.level.impl;

import com.rarait.education.structure.level.LevelService;
import com.rarait.education.structure.level.resource.LevelCreateRequest;
import com.rarait.education.structure.level.resource.LevelResource;
import com.rarait.education.structure.level.resource.LevelUpdateRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.shared.route.InstituteRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.LEVEL)
    public void addLevel(@RequestBody LevelCreateRequest request) {
        levelService.addLevel(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(InstituteRoute.LEVEL_EDIT)
    public void updateLevel(@RequestBody LevelUpdateRequest request) {
        levelService.addLevel(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.LEVEL_ID)
    public LevelResource getLevel(@PathVariable("level_id") int levelId) {
        return levelService.getLevelFromId(levelId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.LEVEL)
    public PagedResponse<LevelResource> getLevelList(
            @RequestParam("page") Integer pageNumber,
            @RequestParam(value = "sort_field", required = false) String sortField,
            @RequestParam(value = "ascend", required = false) Boolean ascend) {
        return levelService.findAllForInstitute(pageNumber, sortField, ascend);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.LEVEL_LIST)
    public List<DropdownListResource> getAllForDropdown() {
        return levelService.findAllForDropdown();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.DEPARTMENT_LIST)
    public List<DropdownListResource> getAllDepartmentForDropdown() {
        return levelService.findAllDepartment();
    }

}
