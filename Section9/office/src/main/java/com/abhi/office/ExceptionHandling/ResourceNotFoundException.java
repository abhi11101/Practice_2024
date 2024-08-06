package com.abhi.office.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, String field , String fieldName) {
        super(String.format("Resource %s is not found with %s : %s", resource, field, fieldName));
    }
}
