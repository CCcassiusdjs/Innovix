package br.com.innovix.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginDTO {

    @Email(message = "Email inválido.")
    @NotBlank
    String email;

    @NotBlank(message = "Senha inválida.")
    String password;
}
