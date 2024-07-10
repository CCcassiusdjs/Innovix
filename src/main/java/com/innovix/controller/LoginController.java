package com.innovix.controller;

import com.innovix.dto.LoginDTO;
import com.innovix.dto.TokenDTO;
import com.innovix.usecase.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * REST controller for handling login operations.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @Autowired
    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    /**
     * Authenticates the user and returns a JWT token.
     *
     * @param loginDTO The login data transfer object.
     * @return The response entity containing the JWT token.
     */
    @PostMapping
    public ResponseEntity<TokenDTO> doLogin(@RequestBody @Valid LoginDTO loginDTO) {
        String token = loginUseCase.login(loginDTO);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
