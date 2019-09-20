package com.rarait.education.login.resource;

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
public class LogoutRequest implements Serializable {

    @JsonProperty("auth_token")
    private String authToken;
}
