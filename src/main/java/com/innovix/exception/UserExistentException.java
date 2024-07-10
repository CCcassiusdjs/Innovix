package com.innovix.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Exception thrown when an attempt is made to create a user that already exists.
 * <p>
 * This class extends RuntimeException and includes validation annotations to ensure
 * the provided email is valid and not blank.
 * </p>
 */
public class UserExistentException extends RuntimeException {

    /**
     * Constructs a new UserExistentException with the specified email.
     * <p>
     * The email must be valid and not blank.
     * </p>
     *
     * @param email The email of the user that already exists.
     */
    public UserExistentException(@Email @NotBlank String email) {
        super("User already exists with email: " + email);
    }
}
