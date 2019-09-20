package com.rarait.education.institute.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rarait.education.login.resource.UserCreateRequest;
import lombok.*;

import java.io.Serializable;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InstituteCreateRequest implements Serializable {

    private String name;
    private String type;
    private String address;

    private String principal;
    private String landline;
    private String website;

    @JsonProperty("registration_no_prefix")
    private String registrationNoPrefix;

    @JsonProperty("contact")
    private ContactCreateRequest contact;

    @JsonProperty("user")
    private UserCreateRequest user;
}