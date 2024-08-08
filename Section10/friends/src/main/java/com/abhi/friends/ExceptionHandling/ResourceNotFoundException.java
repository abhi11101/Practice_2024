package com.abhi.friends.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, String field, String fieldValue) {
        super(String.format("Resource %s not found with %s as %s",resource,field,fieldValue));
    }
}
