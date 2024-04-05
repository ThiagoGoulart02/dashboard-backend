package com.example.dashboard.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LengthException extends RuntimeException{

    private HttpStatus status;

    public LengthException(String message){
        super(message);
        this.status = HttpStatus.LENGTH_REQUIRED;
    }
}