package com.rarait.education.staff.resource;

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
public class EmployeeListResponse implements Serializable {

    private long id;

    @JsonProperty("full_name")
    private String fullName;

    private String type;

    @JsonProperty("primary_contact")
    private String primaryContact;

    @JsonProperty("current_address")
    private String currentAddress;
}
