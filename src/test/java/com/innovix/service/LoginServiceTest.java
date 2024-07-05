package com.innovix.service;

import com.innovix.dto.LoginDTO;
import com.innovix.usecase.LoginUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private LoginUseCase loginUseCase;

    @InjectMocks
    private LoginService loginService;

    private LoginDTO validLoginDTO;
    private String validToken = "validToken";



    @Test
    void testLogin() {
        when(loginUseCase.login(validLoginDTO)).thenReturn(validToken);

        String token = loginService.login(validLoginDTO);

        assertEquals(validToken, token);
        verify(loginUseCase, times(1)).login(validLoginDTO);
    }
}
