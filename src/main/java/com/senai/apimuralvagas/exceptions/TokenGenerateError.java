package com.senai.apimuralvagas.exceptions;

public class TokenGenerateError extends RuntimeException {
    public TokenGenerateError() {
        super("Error ao criar token.");
    }
}



