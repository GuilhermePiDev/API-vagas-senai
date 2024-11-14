package com.senai.apimuralvagas.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(String.format("Usuario com o email:%s, não encontrado" , email ));
    }
}
