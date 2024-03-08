package com.example.dashboard.web.error;

import com.example.dashboard.exceptions.NotFoundException;
import com.example.dashboard.exceptions.RestException;
import com.example.dashboard.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<RestException> userPasswordInvalid(ValidationException exception){
        RestException threatResponse = new RestException(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestException> userFindValidation(NotFoundException exception){
        RestException threatResponse = new RestException(exception.getStatus(), exception.getMessage());
        return ResponseEntity.status(threatResponse.getStatus()).body(threatResponse);
    }
}