package com.senai.apimuralvagas.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(EntityAlreadyExist.class)
    public ResponseEntity<String> handleEntityAlreadyExist(EntityAlreadyExist e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }    
    
    @ExceptionHandler(CustomAccessException.class)
    public ResponseEntity<String> CustomAccessException(CustomAccessException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }    
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> InvalidDataException(InvalidDataException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Extrair o nome do campo e a mensagem de erro personalizada
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTCreate.class)
    public ResponseEntity<String> JWTCreate(JWTCreate e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    } 

    @ExceptionHandler(JWTVerification.class)
    public ResponseEntity<String> JWTVerification(JWTVerification e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    } 




}
