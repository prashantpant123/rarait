package com.rarait.education.exam.util;

import com.rarait.education.exam.impl.Grade;
import com.rarait.education.exam.resource.GradeDetailResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class GradeConvert {

    private GradeConvert() {
    }

    public static GradeDetailResource convertDetail(Grade grade) {
        return GradeDetailResource.builder()
                .id(grade.getId())
                .createdDate(grade.getCreatedDate())
                .lastModifiedDate(grade.getLastModifiedDate())
                .highMark(grade.getHighMark())
                .lowMark(grade.getLowMark())
                .value(grade.getValue())
                .build();
    }

    public static List<GradeDetailResource> convertDetailList(List<Grade> grades){
        return grades.stream()
                .map(GradeConvert::convertDetail)
                .collect(Collectors.toList());
    }
}
