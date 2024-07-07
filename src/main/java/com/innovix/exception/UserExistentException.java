package com.innovix.exception;

public class UserExistentException extends RuntimeException {
    public UserExistentException() {
        super("User already exists!");
    }
}
