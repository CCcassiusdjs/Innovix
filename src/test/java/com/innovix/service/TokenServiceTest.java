package com.innovix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    private Person user;
    private static final String SECRET = "secret";
    private static final String VALID_TOKEN = "valid.token";
    private static final String INVALID_TOKEN = "invalid.token";

    @BeforeEach
    void setUp() {
        tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", SECRET);

        user = new Person();
        user.setEmail("user@example.com");
        user.setType(PersonType.EMPLOYEE);
    }

    @Test
    void createToken_Success() {
        try (MockedStatic<Algorithm> algorithmMockedStatic = Mockito.mockStatic(Algorithm.class);
             MockedStatic<JWT> jwtMockedStatic = Mockito.mockStatic(JWT.class)) {

            var algorithm = Mockito.mock(Algorithm.class);
            algorithmMockedStatic.when(() -> Algorithm.HMAC256(SECRET)).thenReturn(algorithm);

            var jwtBuilder = Mockito.mock(JWTCreator.Builder.class);

            jwtMockedStatic.when(JWT::create).thenReturn(jwtBuilder);
            Mockito.when(jwtBuilder.withIssuer("API innovix")).thenReturn(jwtBuilder);
            Mockito.when(jwtBuilder.withSubject(user.getEmail())).thenReturn(jwtBuilder);
            Mockito.when(jwtBuilder.withClaim("USER_TYPE", user.getType().getValue())).thenReturn(jwtBuilder);
            Mockito.when(jwtBuilder.sign(algorithm)).thenReturn(VALID_TOKEN);

            String token = tokenService.createToken(user);
            assertEquals(VALID_TOKEN, token);
        }
    }

    @Test
    void createToken_Failure() {
        try (MockedStatic<Algorithm> algorithmMockedStatic = Mockito.mockStatic(Algorithm.class)) {
            algorithmMockedStatic.when(() -> Algorithm.HMAC256(SECRET)).thenThrow(JWTCreationException.class);

            assertThrows(RuntimeException.class, () -> tokenService.createToken(user));
        }
    }

    @Test
    void getSubject_Success() {
        try (MockedStatic<Algorithm> algorithmMockedStatic = Mockito.mockStatic(Algorithm.class);
             MockedStatic<JWT> jwtMockedStatic = Mockito.mockStatic(JWT.class)) {

            var algorithm = Mockito.mock(Algorithm.class);
            var verifierBuilder = Mockito.mock(JWTVerifier.BaseVerification.class);
            var verifier = Mockito.mock(JWTVerifier.class);
            var decodedJWT = Mockito.mock(DecodedJWT.class);

            algorithmMockedStatic.when(() -> Algorithm.HMAC256(SECRET)).thenReturn(algorithm);
            jwtMockedStatic.when(() -> JWT.require(algorithm)).thenReturn(verifierBuilder);
            Mockito.when(verifierBuilder.withIssuer("API innovix")).thenReturn(verifierBuilder);
            Mockito.when(verifierBuilder.build()).thenReturn(verifier);
            Mockito.when(verifier.verify(VALID_TOKEN)).thenReturn(decodedJWT);
            Mockito.when(decodedJWT.getSubject()).thenReturn(user.getEmail());

            String subject = tokenService.getSubject(VALID_TOKEN);
            assertEquals(user.getEmail(), subject);
        }
    }

    @Test
    void getSubject_Failure() {
        try (MockedStatic<Algorithm> algorithmMockedStatic = Mockito.mockStatic(Algorithm.class);
             MockedStatic<JWT> jwtMockedStatic = Mockito.mockStatic(JWT.class)) {

            var algorithm = Mockito.mock(Algorithm.class);
            var verifierBuilder = Mockito.mock(JWTVerifier.BaseVerification.class);
            var verifier = Mockito.mock(JWTVerifier.class);

            algorithmMockedStatic.when(() -> Algorithm.HMAC256(SECRET)).thenReturn(algorithm);
            jwtMockedStatic.when(() -> JWT.require(algorithm)).thenReturn(verifierBuilder);
            Mockito.when(verifierBuilder.withIssuer("API innovix")).thenReturn(verifierBuilder);
            Mockito.when(verifierBuilder.build()).thenReturn(verifier);
            Mockito.when(verifier.verify(INVALID_TOKEN)).thenThrow(JWTVerificationException.class);

            assertThrows(RuntimeException.class, () -> tokenService.getSubject(INVALID_TOKEN));
        }
    }
}
