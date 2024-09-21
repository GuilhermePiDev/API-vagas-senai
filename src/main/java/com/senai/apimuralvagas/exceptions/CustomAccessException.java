package com.senai.apimuralvagas.exceptions;

public class CustomAccessException extends IllegalAccessException{

    public CustomAccessException() {
        super("Acesso negado para atualizar o(s) dado(s)");
    }

 
}
