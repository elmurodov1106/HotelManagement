package com.example.hotelmanagement.config;

import com.example.hotelmanagement.exception.AuthenticationFailedException;
import com.example.hotelmanagement.exception.RequestValidationException;
import com.example.hotelmanagement.exception.UniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<String> requestValidationExceptionHandler(
            RequestValidationException e
    ){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(value = {AuthenticationFailedException.class})
    public ResponseEntity<String> authenticationFailedExceptionHandler(
            AuthenticationFailedException e
    ){
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(value = {UniqueObjectException.class})
    public ResponseEntity<String> uniqueObjectException(
            UniqueObjectException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<String> objectNotFoundExceptionHandler(
            ObjectNotFoundException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }


}
