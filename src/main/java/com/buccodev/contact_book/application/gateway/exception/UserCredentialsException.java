package com.buccodev.contact_book.application.gateway.exception;

public class UserCredentialsException extends RuntimeException {
    public UserCredentialsException(String message) {
        super(message);
    }
}
