package com.buccodev.contact_book.infrastructure.services.exceptions;

public class CredentialsInvalidException extends RuntimeException {
    public CredentialsInvalidException(String message) {
        super(message);
    }
}
