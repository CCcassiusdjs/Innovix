package com.innovix.service;

import com.innovix.entity.Person;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class for managing JWT tokens.
 * <p>
 * This class provides methods for creating and verifying JWT tokens.
 * </p>
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String USER_TYPE = "USER_TYPE";

    /**
     * Creates a JWT token for a user.
     *
     * @param user The user for whom to create the token.
     * @return The created JWT token.
     * @throws RuntimeException If an error occurs while generating the token.
     */
    public String createToken(Person user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API innovix")
                    .withSubject(user.getEmail())
                    .withClaim(USER_TYPE, user.getType().getValue())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating JWT token", exception);
        }
    }

    /**
     * Gets the subject (email) from a JWT token.
     *
     * @param tokenJWT The JWT token.
     * @return The subject (email) from the token.
     * @throws RuntimeException If the token is invalid or expired.
     */
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
