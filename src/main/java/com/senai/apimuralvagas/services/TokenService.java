package com.senai.apimuralvagas.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.senai.apimuralvagas.exceptions.TokenExpiradoException;
import com.senai.apimuralvagas.exceptions.TokenGenerateError;
import com.senai.apimuralvagas.exceptions.TokenInvalidoException;



@Service
public class TokenService {

    @Value("apisenaivagas")
    private String secret;

    public String generateToken(UserDetails user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            
            String token = JWT.create()
            .withIssuer("api-mural-vagas")
            .withSubject(user.getUsername())
            .withExpiresAt(expirationDate())
            .sign(algorithm);
            
            return token;
        } catch (JWTCreationException e) {
            throw new TokenGenerateError() ;
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String validate = JWT.require(algorithm)
            .withIssuer("api-mural-vagas")
            .build()
            .verify(token)
            .getSubject();

            return  validate;
            
            } catch (TokenExpiredException e) {
            throw new TokenExpiradoException();
        } catch (JWTVerificationException e) {
            throw new TokenInvalidoException();
        }
    }

    private Instant expirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
