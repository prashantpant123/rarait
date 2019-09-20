package com.rarait.education.student.impl;

import com.rarait.education.document.Document;
import com.rarait.education.shared.route.InstituteRoute;
import com.rarait.education.student.resource.StudentDetailResource;
import com.rarait.education.student.resource.StudentResponse;
import com.rarait.education.utility.BaseConversion;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class StudentConverter {

    private StudentConverter() {
    }

    public static StudentResponse convert(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstname())
                .lastName(student.getLastname())
                .address(student.getAddress())
                .rollNumber(student.getRollNumber())
                .level(student.getLevel().getName())
                .gender(student.getGender().name())
                .registrationId(student.getRegistrationId())
                .build();
    }

    public static List<StudentResponse> convertAll(List<Student> student) {
        return student.stream()
                .map(StudentConverter::convert)
                .collect(Collectors.toList());
    }

    public static StudentDetailResource convertDetail(Student student, Document document, String domain) {
        return StudentDetailResource.builder()
                .firstName(student.getFirstname())
                .lastName(student.getLastname())
                .gender(student.getGender().name())
                .dateOfBirthAd(student.getDateOfBirthAd().toString())
                .dateOfBirthBs(student.getDateOfBirthBs())
                .address(student.getAddress())
                .rollNumber(student.getRollNumber())
                .enrolledDate(student.getEnrolledDate().toString())
                .fatherName(student.getFatherName())
                .motherName(student.getMotherName())
                .guardianName(student.getGuardian())
                .fatherOccupation(student.getFatherOccupation() == null ? "" : student.getFatherOccupation().getName())
                .motherOccupation(student.getMotherOccupation() == null ? "" : student.getMotherOccupation().getName())
                .fatherContact(student.getFatherContact())
                .motherContact(student.getMotherContact())
                .guardianContact(student.getGuardianContact())
                .registrationId(student.getRegistrationId())
                .level(student.getLevel().getName() + "-" + student.getSection().getName())
                .height(student.getHeight())
                .weight(student.getWeight())
                .blood_group(student.getBloodGroup() == null ? "" : student.getBloodGroup().getValue())
                .nationality(student.getNationality() == null ? "" : student.getNationality().getName())
                .transport(student.getTransportRoute() == null ? "" : student.getTransportRoute().getRoute())
                .picture(document == null ? "" : domain + InstituteRoute.INSTITUTE + "/" + BaseConversion.getCode(student.getInstitute().getId())
                        + "/file/" + BaseConversion.getCode(student.getId()) + "/" + document.getFilename())
                .build();
    }
}
