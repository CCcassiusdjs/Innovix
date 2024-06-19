package com.innovix.controller.login;

import com.innovix.dto.LoginDTO;
import com.innovix.dto.PersonDTO;
import com.innovix.dto.TokenDTO;
import com.innovix.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final String USER_CREATED = "User created successfully.";

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/employee")
    public String testEmployee() {
        return "Hello employee.";
    }

    @GetMapping("/customer")
    public String testCustomer() {
        return "Hello customer.";
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<?> registerCustomer(@RequestBody @Valid PersonDTO personDTO) {
        loginService.registerCustomer(personDTO);
        return new ResponseEntity<>(USER_CREATED, HttpStatus.CREATED);
    }

    @PostMapping("/registerEmployee")
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid PersonDTO personDTO) {
        loginService.registerEmployee(personDTO);
        return new ResponseEntity<>(USER_CREATED, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> doLogin(@RequestBody @Valid LoginDTO loginDTO) {
        String token = loginService.login(loginDTO);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
