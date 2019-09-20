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
public class InstituteBasicInfo implements Serializable {

    private String name;
    private String username;

    @JsonProperty("last_login_date")
    private String lastLogin;

    private String logo;

    @JsonProperty("registration_no_prefix")
    private String registrationNoPrefix;

    private String landline;
    private String address;
}
