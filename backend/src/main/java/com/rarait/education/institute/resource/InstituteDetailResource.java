package com.rarait.education.institute.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rarait.education.institute.impl.ContactPerson;
import com.rarait.framework.shared.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class InstituteDetailResource implements Serializable {

    private String name;

    private String address;

    @JsonProperty("logo_path")
    private String logoPath;

    private Status status;

    private String code;

    private String principal;

    private String landline;

    private String website;

    @JsonProperty("registration_prefix")
    private String registrationPrefix;

    private ContactPersonResource contact;

    private String user;
}
