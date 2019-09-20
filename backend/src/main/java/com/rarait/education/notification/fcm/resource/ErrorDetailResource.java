package com.rarait.education.notification.fcm.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDetailResource {

    private int code;

    private String message;

    private String stacktrace;

    @JsonProperty("error_message")
    private String errorMessage;

}