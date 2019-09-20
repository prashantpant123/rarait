package com.rarait.education.structure.department.impl;

import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.structure.department.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public List<DropdownListResource> getAllForDropdown() {
        return DepartmentConvert.convertDropdownList();
    }
}
