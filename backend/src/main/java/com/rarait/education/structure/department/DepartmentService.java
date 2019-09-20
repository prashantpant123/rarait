package com.rarait.education.structure.department;

import com.rarait.education.shared.resource.DropdownListResource;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface DepartmentService {
    List<DropdownListResource> getAllForDropdown();
}
