package com.example.dashboard.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;

@Getter
public class NotFoundException extends RuntimeException {

    private HttpStatus status;
    public NotFoundException(String message){
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
