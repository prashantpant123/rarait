package com.rarait.education.utility.nationality;

import com.rarait.education.shared.resource.DropdownListResource;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface NationalityService {

    void addNationality(String name);

    Nationality findOneById(short nationalityId);

    List<DropdownListResource> findAllNationality();
}
