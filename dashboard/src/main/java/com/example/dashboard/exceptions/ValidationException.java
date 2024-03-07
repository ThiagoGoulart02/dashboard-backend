package com.example.dashboard.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }
}
