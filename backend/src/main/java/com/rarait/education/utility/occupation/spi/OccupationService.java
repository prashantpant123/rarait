package com.rarait.education.utility.occupation.spi;

import com.rarait.education.utility.occupation.impl.Occupation;
import com.rarait.education.utility.occupation.impl.OccupationCreateRequest;
import com.rarait.education.utility.occupation.impl.OccupationResource;
import com.rarait.education.utility.occupation.impl.OccupationUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since Aug 28, 2018
 */
public interface OccupationService {

    Occupation findOneById(Short occupationId);

    void addOccupation(OccupationCreateRequest request);

    @Transactional
    void updateOccupation(OccupationUpdateRequest request);

    List<OccupationResource> findAll();
}
