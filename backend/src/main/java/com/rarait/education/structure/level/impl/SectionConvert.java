package com.rarait.education.structure.level.impl;

import com.rarait.education.structure.level.resource.SectionDetailResource;
import com.rarait.education.structure.level.resource.SectionResource;
import com.rarait.education.shared.resource.DropdownListResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class SectionConvert {

    private SectionConvert() {
    }

    public static SectionResource convert(Section section) {
        return SectionResource.builder()
                .id(section.getId())
                .name(section.getName())
                .level(section.getLevel().getName())
                .status(section.getStatus().toString())
                .build();
    }

    public static List<SectionResource> convertList(List<Section> sections) {
        return sections.stream()
                .map(SectionConvert::convert)
                .collect(Collectors.toList());
    }

    public static SectionDetailResource convertDetail(Section section) {
        return SectionDetailResource.builder()
                .name(section.getName())
                .level(section.getLevel().getName().toString())
                .status(section.getStatus().toString())
                .build();
    }

    public static DropdownListResource convertDropdown(Section section) {
        return DropdownListResource.builder()
                .id(section.getId())
                .name(section.getName())
                .build();
    }

    public static List<DropdownListResource> convertDropdownList(List<Section> section) {
        return section.stream()
                .map(SectionConvert::convertDropdown)
                .collect(Collectors.toList());
    }

}
