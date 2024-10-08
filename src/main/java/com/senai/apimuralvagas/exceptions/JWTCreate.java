package com.senai.apimuralvagas.exceptions;

import com.auth0.jwt.exceptions.JWTCreationException;

public class JWTCreate extends JWTCreationException{

    public JWTCreate(Throwable cause) {
        super("Erro ao gerar token", cause);
        }
    
}
