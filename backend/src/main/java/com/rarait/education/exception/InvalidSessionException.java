package com.rarait.education.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
public class InvalidSessionException extends RuntimeException{

    public InvalidSessionException(String message) {
        super(message);
    }
}
