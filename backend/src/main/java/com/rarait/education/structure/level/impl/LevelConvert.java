package com.rarait.education.structure.level.impl;

import com.rarait.education.structure.level.resource.LevelDetailResource;
import com.rarait.education.structure.level.resource.LevelResource;
import com.rarait.education.shared.resource.DropdownListResource;
import com.rarait.education.shared.resource.MultiDropdownResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class LevelConvert {

    private LevelConvert() {
    }

    public static LevelResource convert(Level level) {
        return LevelResource.builder()
                .id(level.getId())
                .name(level.getName())
                .code(level.getCode())
                .category(level.getDepartment().toString())
                .build();
    }

    public static List<LevelResource> convertList(List<Level> levels) {
        return levels.stream()
                .map(LevelConvert::convert)
                .collect(Collectors.toList());
    }

    public static DropdownListResource convertDropdown(Level level) {
        return DropdownListResource.builder()
                .id(level.getId())
                .name(level.getName())
                .build();
    }

    public static List<DropdownListResource> convertDropdownToList(List<Level> levels) {
        return levels.stream()
                .map(LevelConvert::convertDropdown)
                .collect(Collectors.toList());
    }

    public static LevelDetailResource convertDetail(Level level) {
        return LevelDetailResource.builder()
                .name(level.getName())
                .code(level.getCode())
                .department(level.getDepartment().toString())
                .build();
    }

    public List<MultiDropdownResource> convertClassSectionList() {
        return null;
    }

}
