package com.senai.apimuralvagas.exceptions;

public class TokenExpiradoException extends RuntimeException {
    public TokenExpiradoException() {
        super("O token fornecido está expirado.");
    }
}
