package br.com.innovix.controller.login;

import br.com.innovix.domain.user.TokenDTO;
import br.com.innovix.config.infra.security.TokenService;
import br.com.innovix.domain.user.LoginDTO;
import br.com.innovix.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> doLogin(@RequestBody @Valid LoginDTO login) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.login(), login.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

}