package com.rarait.education.exam.util;

import com.rarait.education.exam.impl.Exam;
import com.rarait.education.exam.impl.ExamTerm;
import com.rarait.education.exam.resource.ExamCategoryResource;
import com.rarait.education.exam.resource.ExamDetailResource;
import com.rarait.education.exam.resource.ExamDropdownResource;
import com.rarait.education.exam.resource.ExamResource;
import com.rarait.education.shared.resource.DropdownListResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class ExamConvert {

    private ExamConvert() {
    }

    public static ExamResource convert(Exam exam) {
        return ExamResource.builder()
                .id(exam.getId())
                .name(exam.getCategory().getName())
                .startDate(exam.getStartDate().toString())
                .endDate(exam.getEndDate().toString())
                .weightage(exam.getWeightage())
                .academicSession(exam.getAcademicSession().getName())
                .build();
    }

    public static List<ExamResource> convertToList(List<Exam> exams) {
        return exams.stream()
                .map(ExamConvert::convert)
                .collect(Collectors.toList());
    }

    public static ExamDetailResource convertDetail(Exam exam) {
        return ExamDetailResource.builder()
                .name(exam.getCategory().getName())
                .startDate(exam.getStartDate().toString())
                .startDateBs(exam.getStartDateBs())
                .endDate(exam.getEndDate().toString())
                .endDateBs(exam.getEndDateBs())
                .weightage(exam.getWeightage())
                .academicSession(exam.getAcademicSession().getName())
                .build();
    }

    public static ExamDropdownResource convertDropdown(Exam exam) {
        return ExamDropdownResource.builder()
                .id(exam.getId())
                .name(exam.getCategory().getName())
                .build();
    }

    public static List<ExamDropdownResource> convertDropdownList(List<Exam> exams) {
        return exams.stream()
                .map(ExamConvert::convertDropdown)
                .collect(Collectors.toList());
    }

    public static DropdownListResource convertExamSession(Exam exam) {
        return DropdownListResource.builder()
                .id(exam.getId())
                .name(exam.getAcademicSession().getName() + " - " + exam.getCategory().getName())
                .build();
    }

    public static List<DropdownListResource> convertExamSessionList(List<Exam> exams) {
        return exams.stream()
                .map(ExamConvert::convertExamSession)
                .collect(Collectors.toList());
    }

    public static ExamCategoryResource convertTerm(ExamTerm examTerm) {
        return ExamCategoryResource.builder()
                .id(examTerm.getId())
                .name(examTerm.getName())
                .weightage(examTerm.getWeightage())
                .status(examTerm.getStatus().toString())
                .build();
    }

    public static List<ExamCategoryResource> convertTermList(List<ExamTerm> categories) {
        return categories.stream()
                .map(ExamConvert::convertTerm)
                .collect(Collectors.toList());
    }

    public static DropdownListResource convertTermDropdown(ExamTerm exam) {
        return DropdownListResource.builder()
                .id(exam.getId())
                .name(exam.getName())
                .build();
    }

    public static List<DropdownListResource> convertTermDropdownList(List<ExamTerm> categories) {
        return categories.stream()
                .map(ExamConvert::convertTermDropdown)
                .collect(Collectors.toList());
    }

}
