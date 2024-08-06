package com.abhi.friends.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDTO {

    private String apiPath;
    private HttpStatus errorStatusCode;
    private String errorMessage;
    private LocalDateTime errorTime;

}
