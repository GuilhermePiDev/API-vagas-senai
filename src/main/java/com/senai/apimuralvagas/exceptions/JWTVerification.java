package com.senai.apimuralvagas.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTVerification extends JWTVerificationException{

    public JWTVerification() {
        super("Erro na verificação do token, invalido ou não autorizado");
        }
    
}
