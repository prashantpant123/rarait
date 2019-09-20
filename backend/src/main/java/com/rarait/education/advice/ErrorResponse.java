package com.rarait.education.advice;

import lombok.Getter;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
public class ErrorResponse {

    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
