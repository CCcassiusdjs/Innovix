package com.innovix.usecase;

import com.innovix.dto.LoginDTO;
import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import com.innovix.service.PersonService;
import com.innovix.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Use case class for handling login operations.
 * <p>
 * This class provides methods to authenticate users and generate JWT tokens.
 * </p>
 */
@Component
public class LoginUseCase {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final PersonService personService;

    /**
     * Constructs a new LoginUseCase.
     *
     * @param manager       The authentication manager.
     * @param tokenService  The token service.
     * @param personService The person service.
     */
    @Autowired
    public LoginUseCase(AuthenticationManager manager, TokenService tokenService, PersonService personService) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.personService = personService;
    }

    /**
     * Authenticates the user and generates a JWT token.
     *
     * @param loginDTO The login data transfer object containing email and password.
     * @return The generated JWT token.
     * @throws IllegalStateException If the user type is unknown.
     */
    public String login(LoginDTO loginDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var authentication = manager.authenticate(authenticationToken);
        Person person = (Person) authentication.getPrincipal();

        if (person.getType() == PersonType.CUSTOMER || person.getType() == PersonType.EMPLOYEE) {
            return tokenService.createToken(person);
        } else {
            throw new IllegalStateException("Unknown user type");
        }
    }
}
