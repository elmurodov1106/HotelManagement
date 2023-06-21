package com.example.hotelmanagement.exception;


public class AuthenticationFailedException extends RuntimeException {

    public AuthenticationFailedException(String message){
        super(message);
    }
 }
