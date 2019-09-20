package com.rarait.education.login.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PdfRequest implements Serializable {

    @JsonProperty("auth_token")
    private String authToken;

}
