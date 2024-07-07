package com.innovix.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PersonDTO {

    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
    private String cpf;

    @NotBlank
    private String password;

    @NotBlank
    private String phone;

    @NotBlank
    private String birthday;

    @NotBlank
    private String type;
}
