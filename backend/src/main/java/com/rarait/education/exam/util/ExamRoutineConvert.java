package com.rarait.education.exam.util;

import com.rarait.education.exam.impl.ExamRoutine;
import com.rarait.education.structure.subject.impl.Subject;
import com.rarait.education.exam.resource.ExamRoutineResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class ExamRoutineConvert {

    private ExamRoutineConvert() {
    }

    public static ExamRoutineResource convertSchedule(ExamRoutine examRoutine) {
        return ExamRoutineResource.builder()
                .id(examRoutine.getId())
                .examDate(examRoutine.getExamDate())
                .startTime(examRoutine.getStartTime())
                .endTime(examRoutine.getEndTime())
                .fullMarks(examRoutine.getFullMark())
                .passMarks(examRoutine.getPassMark())
                .subjectId(examRoutine.getSubject().getId())
                .subject(examRoutine.getSubject().getName())
                .remarks(examRoutine.getRemarks())
                .build();
    }

    public static List<ExamRoutineResource> convertScheduleToList(List<ExamRoutine> examRoutines) {
        return examRoutines.stream()
                .map(ExamRoutineConvert::convertSchedule)
                .collect(Collectors.toList());
    }

    public static ExamRoutineResource convertSubject(Subject subject) {
        return ExamRoutineResource.builder()
                .subjectId(subject.getId())
                .subject(subject.getName())
                .fullMarks(subject.getTheoryFullMark())
                .passMarks(subject.getTheoryPassMark())
                .build();
    }

    public static List<ExamRoutineResource> convertSubjectList(List<Subject> subjects) {
        return subjects.stream()
                .map(ExamRoutineConvert::convertSubject)
                .collect(Collectors.toList());
    }
}
