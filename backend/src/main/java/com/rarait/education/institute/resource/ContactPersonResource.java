package com.rarait.education.institute.resource;

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
public class ContactPersonResource implements Serializable {

    private Integer id;

    @JsonProperty("full_name")
    private String fullname;

    private String designation;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("landline_number")
    private String landline;

    @JsonProperty("email_id")
    private String emailId;
}
