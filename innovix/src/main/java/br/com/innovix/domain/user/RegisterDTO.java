package br.com.innovix.domain.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterDTO extends LoginDTO {

        @Enumerated(EnumType.STRING)
        UserTypeEnum type;

}