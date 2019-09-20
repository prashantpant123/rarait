package com.rarait.education.student.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class StudentRegisterRequest implements Serializable {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    @JsonProperty("father_name")
    private String fatherName;

    @JsonProperty("father_occupation_id")
    private short fatherOccupationId;

    @JsonProperty("father_contact")
    private String fatherContact;

    @JsonProperty("mother_name")
    private String motherName;

    @JsonProperty("mother_occupation_id")
    private short motherOccupationId;

    @JsonProperty("mother_contact")
    private String motherContact;

    @JsonProperty("guardian_name")
    private String guardianName;

    @JsonProperty("guardian_contact")
    private String guardianContact;

    private int gender;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("class_id")
    private int classId;

    @JsonProperty("section_id")
    private int sectionId;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("registration_number")
    private String registrationNumber;

    @JsonProperty("bus_route_id")
    private int busRouteId;

    private float weight;

    private float height;

    @JsonProperty("nationality_id")
    private short nationalityId;

    @JsonProperty("blood_group")
    private int bloodGroup;
}