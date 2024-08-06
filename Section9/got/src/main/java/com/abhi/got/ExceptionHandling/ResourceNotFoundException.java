package com.abhi.got.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, String field, String fieldValue) {
        super(String.format("Resource %s not found with %s: %s", resource, field, fieldValue));
    }
}
