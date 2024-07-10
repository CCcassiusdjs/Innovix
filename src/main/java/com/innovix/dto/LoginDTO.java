package com.innovix.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for login credentials.
 * <p>
 * This class is used to transfer login credentials between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 *
 * @param email    The email address of the user. This field is mandatory and must be a valid email format.
 * @param password The password of the user. This field is mandatory.
 */
public record LoginDTO(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password
) {}
