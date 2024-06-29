package com.abhi.friends.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EpisodeAlreadyExistsException extends RuntimeException {

    public EpisodeAlreadyExistsException(String message) {
        super(String.format("Episode %s already exists", message));
    }
}
