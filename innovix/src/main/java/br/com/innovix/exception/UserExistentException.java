package br.com.innovix.exception;

import br.com.innovix.domain.user.UserTypeEnum;

public class UserExistentException extends RuntimeException {

    private static final String message = "Usuário já existente.";


    public UserExistentException() {
        super(message);

    }
}
