package com.pm.patientservice.exception;

import org.springframework.validation.Errors;

public class EmailAlreayExistsException extends RuntimeException{
    public EmailAlreayExistsException(String message)
    {
        super(message);
    }

}
