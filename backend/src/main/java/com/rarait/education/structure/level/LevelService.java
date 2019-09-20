package com.rarait.education.structure.level;

import com.rarait.education.structure.level.impl.Level;
import com.rarait.education.structure.level.resource.LevelCreateRequest;
import com.rarait.education.structure.level.resource.LevelResource;
import com.rarait.education.structure.level.resource.LevelUpdateRequest;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface LevelService {

    void addLevel(LevelCreateRequest request);

    void updateLevel(LevelUpdateRequest request);

    Level getLevelById(int levelId);

    Level findOneByIdAndInstitute(int instituteId, int levelId);

    LevelResource getLevelFromId(int levelId);

    PagedResponse<LevelResource> findAllForInstitute(int pageNumber, String sortField, Boolean ascend);

    List<DropdownListResource> findAllForDropdown();

    List<DropdownListResource> findAllDepartment();
}
