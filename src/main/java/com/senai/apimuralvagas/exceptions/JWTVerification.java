package com.senai.apimuralvagas.exceptions;



public class JWTVerification extends RuntimeException{

    public JWTVerification() {
        super("Erro na verificação do token, invalido ou não autorizado ");
        }
    
}
