package com.senai.apimuralvagas.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String anypropertyName, String anyProperty){
        super(String.format("a propriedade %s: %s está incorreta", anyProperty, anypropertyName));
    }
}
