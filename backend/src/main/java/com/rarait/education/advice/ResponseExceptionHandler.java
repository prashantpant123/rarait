package com.rarait.education.advice;

import com.rarait.framework.exception.ClientRestException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static String GENERAL_REPLY = "Sorry, your request could not be processed.";
    private static String BAD_CRED = "Invalid username or password";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {
        log.error("Error: ", ex);
        return new ResponseEntity<>(new ErrorResponse(GENERAL_REPLY), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadCredentialsException.class, ExpiredJwtException.class})
    public ResponseEntity<ErrorResponse> invalidLogin() {
        return new ResponseEntity<>(new ErrorResponse(BAD_CRED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientRestException.class)
    public ResponseEntity<ErrorResponse> clientError(ClientRestException cre) {
        log.error("Error: ", cre);
        return new ResponseEntity<>(new ErrorResponse(cre.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
