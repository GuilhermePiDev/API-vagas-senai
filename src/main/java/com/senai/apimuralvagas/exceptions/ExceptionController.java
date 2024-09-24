package com.senai.apimuralvagas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
