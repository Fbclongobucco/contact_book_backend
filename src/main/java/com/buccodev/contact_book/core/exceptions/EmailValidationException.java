package com.buccodev.contact_book.core.exceptions;

public class EmailValidationException extends RuntimeException {
    public EmailValidationException(String message) {
        super(message);
    }
}
