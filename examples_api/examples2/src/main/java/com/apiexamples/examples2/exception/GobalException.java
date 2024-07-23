package com.apiexamples.examples2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalException {

    @ExceptionHandler
    public ResponseEntity<String> resourceNotFoundException(
            ResourceNotFoundException e
    ){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }
}
