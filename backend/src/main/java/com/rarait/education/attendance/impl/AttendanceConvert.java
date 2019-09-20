package com.rarait.education.attendance.impl;

import com.rarait.education.attendance.AttendanceStatus;
import com.rarait.education.attendance.resource.AttendanceDropdownResource;
import com.rarait.education.attendance.resource.AttendanceResponse;
import com.rarait.education.student.impl.Student;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class AttendanceConvert {

    private AttendanceConvert() {
    }

    public static AttendanceDropdownResource convertType(AttendanceStatus type) {
        return AttendanceDropdownResource.builder()
                .id(type.getValue())
                .name(type.name())
                .build();
    }

    public static List<AttendanceDropdownResource> convertTypeList() {
        return new ArrayList<>(EnumSet.allOf(AttendanceStatus.class))
                .stream()
                .map(AttendanceConvert::convertType)
                .collect(Collectors.toList());
    }

//    public static AttendanceResponse convert(Attendance attendance) {
//        return AttendanceResponse.builder()
//                .id(attendance.getId())
//                .status(attendance.getStatus().getValue())
//                .rollNumber(attendance.getStudent().getRollNumber())
//                .studentName(attendance.getStudent().getFirstname() + " " + attendance.getStudent().getLastname())
//                .build();
//    }
//
//    public static List<AttendanceResponse> convertList(List<Attendance> attendances) {
//        return attendances.stream()
//                .map(AttendanceConvert::convert)
//                .collect(Collectors.toList());
//    }
//
//    public static AttendanceResponse convertStudent(Student student) {
//        return AttendanceResponse.builder()
//                .rollNumber(student.getRollNumber())
//                .studentName(student.getFirstname() + " " + student.getLastname())
//                .studentId(student.getId())
//                .build();
//    }
//
//    public static List<AttendanceResponse> convertStudentList(List<Student> students) {
//        return students.stream()
//                .map(AttendanceConvert::convertStudent)
//                .collect(Collectors.toList());
//    }
}
