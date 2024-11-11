package com.senai.apimuralvagas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Entity Not Found", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EntityAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleEntityAlreadyExist(EntityAlreadyExist e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Entity Already Exists", e.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(CustomAccessException.class)
    public ResponseEntity<ErrorResponse> handleCustomAccessException(CustomAccessException e,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized Access", e.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDataException e,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Data", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTCreate.class)
    public ResponseEntity<ErrorResponse> handleJWTCreateException(JWTCreate e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "JWT Creation Error", e.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(JWTVerification.class)
    public ResponseEntity<ErrorResponse> handleJWTVerificationException(JWTVerification e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "JWT Verification Error", e.getMessage(),
                request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String error, String message,
            String path) {
        ErrorResponse errorResponse = new ErrorResponse(status.value(), error, message, path);
        return new ResponseEntity<>(errorResponse, status);
    }
}
