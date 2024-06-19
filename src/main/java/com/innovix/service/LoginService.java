package com.innovix.service;

import com.innovix.dto.LoginDTO;
import com.innovix.dto.PersonDTO;
import com.innovix.usecase.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginUseCase loginUseCase;

    @Autowired
    public LoginService(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public String login(LoginDTO loginDTO) {
        return loginUseCase.login(loginDTO);
    }

    public void registerCustomer(PersonDTO personDTO) {
        loginUseCase.registerCustomer(personDTO);
    }

    public void registerEmployee(PersonDTO personDTO) {
        loginUseCase.registerEmployee(personDTO);
    }
}
