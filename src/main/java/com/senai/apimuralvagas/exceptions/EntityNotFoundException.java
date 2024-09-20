package com.senai.apimuralvagas.exceptions;

public class EntityNotFoundException extends RuntimeException {
    
    public EntityNotFoundException(String entityName, Integer entityId) {
        super(String.format("%s com o id %d não foi encontrado", entityName, entityId));
    }
}
