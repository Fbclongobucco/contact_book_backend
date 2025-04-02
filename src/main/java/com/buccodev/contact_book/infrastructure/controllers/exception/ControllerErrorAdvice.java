package com.buccodev.contact_book.infrastructure.controllers.exception;

import com.buccodev.contact_book.application.gateway.exception.*;
import com.buccodev.contact_book.core.exceptions.EmailValidationException;
import com.buccodev.contact_book.core.exceptions.NumberValidationException;
import com.buccodev.contact_book.core.exceptions.PasswordValidationException;
import com.buccodev.contact_book.infrastructure.services.exceptions.CredentialsInvalidException;
import com.buccodev.contact_book.infrastructure.services.exceptions.InvalidQueryParametersException;
import com.buccodev.contact_book.infrastructure.services.exceptions.ResourceNotFoundException;
import com.buccodev.contact_book.infrastructure.services.exceptions.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerErrorAdvice {

    private ResponseEntity<StandardError> buildErrorResponse(Exception e, HttpServletRequest request, HttpStatus status, List<Map<String, String>> errors) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            return ResponseEntity.status(status).body(new StandardError(timestamp, status.value(), request.getRequestURI(), errors));

    }

    @ExceptionHandler({
            EmailValidationException.class,
            NumberValidationException.class,
            PasswordValidationException.class,
            UserAlreadyExistsException.class
    })
    public ResponseEntity<StandardError> handleValidationExceptions(Exception e, HttpServletRequest request) {
        return buildErrorResponse(e, request, HttpStatus.UNPROCESSABLE_ENTITY, List.of(Map.of("message", e.getMessage())));
    }

    @ExceptionHandler({
            ContactDuplicateException.class,
            UserDuplicateException.class
    })
    public ResponseEntity<StandardError> handleDuplicateExceptions(Exception e, HttpServletRequest request) {
        return buildErrorResponse(e, request, HttpStatus.CONFLICT, List.of(Map.of("message", e.getMessage())));
    }

    @ExceptionHandler({
            ContactNotFoundException.class,
            UserNotFoundException.class,
            ResourceNotFoundException.class
    })
    public ResponseEntity<StandardError> handleNotFoundExceptions(Exception e, HttpServletRequest request) {
        return buildErrorResponse(e, request, HttpStatus.NOT_FOUND, List.of(Map.of("message", e.getMessage())));
    }

    @ExceptionHandler({
            UserCredendialsException.class,
            CredentialsInvalidException.class
    })
    public ResponseEntity<StandardError> handleUnauthorizedExceptions(Exception e, HttpServletRequest request) {
        return buildErrorResponse(e, request, HttpStatus.UNAUTHORIZED, List.of(Map.of("message", e.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<Map<String, String>> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> Map.of(
                        "field", fieldError.getField(),
                        "message", fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        return buildErrorResponse(e, request, status, errors);
    }

    @ExceptionHandler(InvalidQueryParametersException.class)
    public ResponseEntity<StandardError> handleInvalidQueryParameters(InvalidQueryParametersException e, HttpServletRequest request) {
        return buildErrorResponse(e, request, HttpStatus.BAD_REQUEST, List.of(Map.of("message", e.getMessage())));
    }
}
