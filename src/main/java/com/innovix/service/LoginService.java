package com.innovix.service;

import com.innovix.dto.LoginDTO;
import com.innovix.usecase.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing login operations.
 * <p>
 * This class provides methods for user authentication.
 * </p>
 */
@Service
public class LoginService {

    private final LoginUseCase loginUseCase;

    @Autowired
    public LoginService(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param loginDTO The login data transfer object containing the user's credentials.
     * @return The JWT token for the authenticated user.
     */
    public String login(LoginDTO loginDTO) {
        return loginUseCase.login(loginDTO);
    }
}
