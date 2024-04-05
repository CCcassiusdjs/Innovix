package br.com.innovix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SaleItemNotFoundException extends RuntimeException {

    public SaleItemNotFoundException(String message) {
        super(message);
    }
}
