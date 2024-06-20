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

@Component
public class LoginUseCase {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final PersonService personService;

    @Autowired
    public LoginUseCase(AuthenticationManager manager, TokenService tokenService, PersonService personService) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.personService = personService;
    }

    public String login(LoginDTO loginDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var authentication = manager.authenticate(authenticationToken);
        Person person = (Person) authentication.getPrincipal();

        if (person.getType() == PersonType.CUSTOMER) {
            return tokenService.createToken(person);
        } else if (person.getType() == PersonType.EMPLOYEE) {
            return tokenService.createToken(person);
        } else {
            throw new IllegalStateException("Unknown user type");
        }
    }
}
