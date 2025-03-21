package com.buccodev.contact_book.application.gateway.exception;

public class ContactDuplicateException extends RuntimeException {
    public ContactDuplicateException(String message) {
        super(message);
    }
}
