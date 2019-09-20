package com.rarait.education.structure.subject.impl;

import com.rarait.education.structure.resource.SubjectResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class SubjectConvert {

    private SubjectConvert() {
    }

    public static SubjectResource convert(Subject subject) {
        return SubjectResource.builder()
                .id(subject.getId())
                .code(subject.getCode())
                .name(subject.getName())
                .description(subject.getDescription())
                .optional(subject.isOptional())
                .practical(subject.isPractical())
                .fullMark(subject.getTheoryFullMark())
                .passMark(subject.getTheoryPassMark())
                .practicalFullMark(subject.getPracticalFullMark())
                .practicalPassMark(subject.getPracticalPassMark())
                .levelId(subject.getLevel().getId())
                .levelName(subject.getLevel().getName())
                .build();
    }

    public static List<SubjectResource> convertList(List<Subject> subjects) {
        return subjects.stream()
                .map(SubjectConvert::convert)
                .collect(Collectors.toList());
    }

//    public static SubjectListResource covertToList(Subject subject){
//        return  SubjectListResource.builder()
//                .level()
//                .build();
//    }
//
//    public static List<SubjectListResource> convertToReport(List<Subject> subjects){
//        return subjects.stream()
//                .map()
//                .collect(Collectors.toList());
//    }
}
