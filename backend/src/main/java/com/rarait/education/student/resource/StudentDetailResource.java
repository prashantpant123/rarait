package com.rarait.education.student.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class StudentDetailResource implements Serializable {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("registration_id")
    private String registrationId;

    private String level;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("father_name")
    private String fatherName;

    @JsonProperty("father_occupation")
    private String fatherOccupation;

    @JsonProperty("father_contact")
    private String fatherContact;

    @JsonProperty("mother_name")
    private String motherName;

    @JsonProperty("mother_occupation")
    private String motherOccupation;

    @JsonProperty("mother_contact")
    private String motherContact;

    @JsonProperty("guardian_name")
    private String guardianName;

    @JsonProperty("guardian_contact")
    private String guardianContact;

    private String address;

    @JsonProperty("date_of_birth_ad")
    private String dateOfBirthAd;

    @JsonProperty("date_of_birth_bs")
    private String dateOfBirthBs;

    private String gender;

    @JsonProperty("enrolled_date")
    private String enrolledDate;

    private float weight;

    private float height;

    private String picture;

    private String nationality;

    private String blood_group;

    private String transport;

}
