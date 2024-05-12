package br.com.innovix.controller.login;

import br.com.innovix.domain.user.*;
import br.com.innovix.config.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final String USER_CREATED = "Usuário criado com sucesso.";

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/employee")
    public String testeEmployee() {
        return "Hello employee.";

    }

    @GetMapping("/client")
    public String testeClient() {
        return "Hello client.";

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO loginDTO) {
        loginService.register(loginDTO);
        return ResponseEntity.ok(USER_CREATED);
    }

    @PostMapping
    public ResponseEntity<?> doLogin(@RequestBody @Valid LoginDTO login) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}