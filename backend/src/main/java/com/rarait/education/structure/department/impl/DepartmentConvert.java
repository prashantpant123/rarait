package com.rarait.education.structure.department.impl;

import com.rarait.education.shared.resource.DropdownListResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class DepartmentConvert {

    private DepartmentConvert() {
    }

    public static List<DropdownListResource> convertDropdownList() {
        return Arrays.asList(DepartmentType.values())
                .stream()
                .map(DepartmentConvert::convertDropdown)
                .collect(Collectors.toList());
    }

    public static DropdownListResource convertDropdown(DepartmentType type) {
        return DropdownListResource.builder()
                .id(type.ordinal())
                .name(type.toString())
                .build();

    }
}
