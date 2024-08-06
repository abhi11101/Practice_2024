package com.abhi.office.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EpisodeAlreadyExistException extends RuntimeException {

    public EpisodeAlreadyExistException(String message) {
        super(message);
    }
}
