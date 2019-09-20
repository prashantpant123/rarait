package com.rarait.education.login.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PdfResponse implements Serializable {
    private String message;

    public PdfResponse(String message) {
        this.message = message;
    }
}
