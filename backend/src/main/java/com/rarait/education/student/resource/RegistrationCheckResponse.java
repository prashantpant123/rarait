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
@Getter
@Builder
@ToString
public class RegistrationCheckResponse implements Serializable {

    private boolean exist;

    @JsonProperty("registration_number")
    private String registrationNumber;
}
