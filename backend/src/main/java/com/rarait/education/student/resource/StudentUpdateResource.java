package com.rarait.education.student.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class StudentUpdateResource extends StudentRegisterRequest {

    @JsonProperty("registration_id")
    private String registrationId;

    private String remarks;

}
