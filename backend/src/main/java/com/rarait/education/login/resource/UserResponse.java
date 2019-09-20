package com.rarait.education.login.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Builder
@Getter
@ToString
public class UserResponse implements Serializable {

    private long id;
    private String username;

    @JsonProperty("last_login_date")
    private Date lastLoginDate;

    private String role;

    private String status;
}
