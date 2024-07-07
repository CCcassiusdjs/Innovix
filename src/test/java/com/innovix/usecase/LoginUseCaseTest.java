package com.innovix.usecase;

import com.innovix.dto.LoginDTO;
import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import com.innovix.service.PersonService;
import com.innovix.service.TokenService;
import com.innovix.usecase.LoginUseCase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoginUseCaseTest {

    @Mock
    private AuthenticationManager manager;

    @Mock
    private TokenService tokenService;

    @Mock
    private PersonService personService;

    @InjectMocks
    private LoginUseCase loginUseCase;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.jupiter.api.Test
    void loginCustomer() {
        // Simulate data
        LoginDTO loginDTO = new LoginDTO("customer@example.com", "password");
        Person customer = new Person();
        customer.setType(PersonType.CUSTOMER);
        PersonDTO customerDTO = new PersonDTO();
        customerDTO.setType("CUSTOMER");

        // Mock behavior
        when(manager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(manager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(manager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));
        when(personService.findByEmail(loginDTO.email())).thenReturn(customerDTO);
        when(tokenService.createToken(customer)).thenReturn("generated_token");

        // Test
        String result = loginUseCase.login(loginDTO);

        // Verify interactions and assertions
        assertEquals("generated_token", result);
        verify(manager, times(1)).authenticate(any());
        verify(personService, times(1)).findByEmail(loginDTO.email());
        verify(tokenService, times(1)).createToken(customer);
        verifyNoMoreInteractions(manager, personService, tokenService);
    }

    @org.junit.jupiter.api.Test
    void loginEmployee() {
        // Simulate data
        LoginDTO loginDTO = new LoginDTO("employee@example.com", "password");
        Person employee = new Person();
        employee.setType(PersonType.EMPLOYEE);
        PersonDTO employeeDTO = new PersonDTO();
        employeeDTO.setType("EMPLOYEE");

        // Mock behavior
        when(manager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(personService.findByEmail(loginDTO.email())).thenReturn(employeeDTO);
        when(tokenService.createToken(employee)).thenReturn("generated_token");

        // Test
        String result = loginUseCase.login(loginDTO);

        // Verify interactions and assertions
        assertEquals("generated_token", result);
        verify(manager, times(1)).authenticate(any());
        verify(personService, times(1)).findByEmail(loginDTO.email());
        verify(tokenService, times(1)).createToken(employee);
        verifyNoMoreInteractions(manager, personService, tokenService);
    }

    @org.junit.jupiter.api.Test
    void loginUnknownUserType() {
        // Simulate data
        LoginDTO loginDTO = new LoginDTO("unknown@example.com", "password");
        Person unknownPerson = new Person();
        unknownPerson.setType(null);
        PersonDTO unknownPersonDTO = new PersonDTO();
        unknownPersonDTO.setType(null);

        // Mock behavior
        when(manager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(personService.findByEmail(loginDTO.email())).thenReturn(unknownPersonDTO);

        // Test and verify exception
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> loginUseCase.login(loginDTO));
        assertEquals("Unknown user type", exception.getMessage());

        // Verify interactions
        verify(manager, times(1)).authenticate(any());
        verify(personService, times(1)).findByEmail(loginDTO.email());
        verifyNoMoreInteractions(manager, personService, tokenService);
    }
}
