package com.buccodev.contact_book.infrastructure.services.exceptions;

public class InvalidQueryParametersException extends RuntimeException {
  public InvalidQueryParametersException(String message) {
    super(message);
  }
}
