package com.senai.apimuralvagas.exceptions;

public class EntityAlreadyExist extends RuntimeException{
    

    public EntityAlreadyExist(String entityName, String anypropertyName, String anyProperty) {
        super(String.format("%s com o %s %s já existe", entityName, anypropertyName, anyProperty));
    }
}
