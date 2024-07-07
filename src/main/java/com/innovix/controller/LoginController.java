package com.innovix.controller;

import com.innovix.dto.LoginDTO;
import com.innovix.dto.TokenDTO;
import com.innovix.usecase.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @Autowired
    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> doLogin(@RequestBody @Valid LoginDTO loginDTO) {
        String token = loginUseCase.login(loginDTO);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
