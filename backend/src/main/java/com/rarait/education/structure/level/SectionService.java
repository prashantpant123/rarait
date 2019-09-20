package com.rarait.education.structure.level;

import com.rarait.education.structure.level.impl.Section;
import com.rarait.education.structure.level.resource.SectionCreateRequest;
import com.rarait.education.structure.level.resource.SectionDetailResource;
import com.rarait.education.structure.level.resource.SectionResource;
import com.rarait.education.shared.PagedResponse;
import com.rarait.education.shared.resource.DropdownListResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface SectionService {

    void createSection(SectionCreateRequest request);

    PagedResponse<SectionResource> findAllSection(int levelId, int pageNumber);

    Section findOneByIdAndInstitute(int instituteId, int sectionId);

    SectionDetailResource findOneInfo(int sectionId);

    List<DropdownListResource> findAllForList(int levelId);
}
