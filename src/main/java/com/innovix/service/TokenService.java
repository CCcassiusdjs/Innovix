package com.innovix.service;

import com.innovix.entity.Person;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String USER_TYPE = "USER_TYPE";

    public String createToken(Person user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API innovix")
                    .withSubject(user.getEmail())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating JWT token", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API innovix")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired JWT token!");
        }
    }
}
