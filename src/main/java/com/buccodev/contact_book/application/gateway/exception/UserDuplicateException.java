package com.buccodev.contact_book.application.gateway.exception;

public class UserDuplicateException extends RuntimeException {
    public UserDuplicateException(String userAlreadyExists) {
        super(userAlreadyExists);
    }
}
