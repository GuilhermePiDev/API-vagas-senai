package com.senai.apimuralvagas.exceptions;

public class CustomAccessException extends IllegalAccessException{

    public CustomAccessException() {
        super("Usuario sem permissão para executar essa ação");
    }

 
}
