package com.management.hrr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{
    String resourceName;
    public ConflictException(String resourceName) {
        super(String.format("The %s is not available", resourceName));
        this.resourceName = resourceName;
    }
}
