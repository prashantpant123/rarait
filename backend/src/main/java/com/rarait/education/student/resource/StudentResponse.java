package com.rarait.education.student.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class StudentResponse implements Serializable{

    private long id;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    private String level;

    private String gender;

    @JsonProperty("registration_id")
    private String registrationId;
}