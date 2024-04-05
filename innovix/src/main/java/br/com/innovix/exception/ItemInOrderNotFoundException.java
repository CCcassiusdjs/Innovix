package br.com.innovix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemInOrderNotFoundException extends RuntimeException {

    public ItemInOrderNotFoundException(String message) {
        super(message);
    }
}
