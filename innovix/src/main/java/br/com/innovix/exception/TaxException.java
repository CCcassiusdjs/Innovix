package br.com.innovix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaxException extends RuntimeException {

    public TaxException(String message) {
        super(message);
    }
}
