package com.example.dashboard.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationException extends RuntimeException {

    private HttpStatus status;

    public ValidationException(String message){
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
