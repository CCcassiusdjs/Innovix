package com.innovix.service;

import com.innovix.entity.Person;
import com.innovix.entity.PersonType; // Assuming PersonType enum is defined
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @Mock
    private Algorithm algorithm;

    @Value("${api.security.token.secret}")
    private String secret;

    @InjectMocks
    private TokenService tokenService;

    private Person user;

    @BeforeEach
    void setUp() {
        user = new Person();
        user.setEmail("test@example.com");
        user.setType(PersonType.CUSTOMER);
    }

    @Test
    void testCreateToken() {
        try {
            String mockedToken = "mockedToken";
            when(algorithm.sign(any())).thenAnswer(invocation -> mockedToken);

            String token = tokenService.createToken(user);

            assertNotNull(token);
            assertTrue(token.startsWith(mockedToken));
        } catch (JWTCreationException exception) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testCreateTokenException() {
        try {
            when(algorithm.sign(any())).thenThrow(new JWTCreationException("Failed to create token", new Throwable()));

            assertThrows(RuntimeException.class, () -> {
                tokenService.createToken(user);
            });
        } catch (JWTCreationException exception) {
            fail("Unexpected JWTCreationException");
        }
    }
}
