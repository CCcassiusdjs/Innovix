package com.innovix.dto;

/**
 * Data Transfer Object for authentication token.
 * <p>
 * This class is used to transfer authentication token data between different layers of the application.
 * </p>
 *
 * @param token The authentication token.
 */
public record TokenDTO(String token) {}
