package com.abhi.got.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDTO {

    private String apiPath;
    private HttpStatus errorStatus;
    private String errorMessage;
    private LocalDateTime errorTime;

}
