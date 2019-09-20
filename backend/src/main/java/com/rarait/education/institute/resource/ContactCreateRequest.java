package com.rarait.education.institute.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class ContactCreateRequest implements Serializable {

    @JsonProperty("full_name")
    private String fullname;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("landline_number")
    private String landlineNumber;

    private String designation;

}
