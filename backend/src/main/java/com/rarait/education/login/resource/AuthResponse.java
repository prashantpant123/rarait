package com.rarait.education.login.resource;

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
public class AuthResponse implements Serializable {

    @JsonProperty("auth_token")
    private String token;
    private String username;
    private String role;

}
