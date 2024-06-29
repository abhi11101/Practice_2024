package com.abhi.series.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SeriesAlreadyExistException extends RuntimeException {

    public SeriesAlreadyExistException(String message) {
        super(String.format("Series %s already exists", message));
    }
}
