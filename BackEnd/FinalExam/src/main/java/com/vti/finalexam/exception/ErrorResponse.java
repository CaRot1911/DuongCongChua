package com.vti.finalexam.exception;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {

    @NonNull
    private HttpStatus status;

    @NonNull
    private String message;
}
