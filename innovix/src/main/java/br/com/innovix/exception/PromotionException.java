package br.com.innovix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PromotionException extends RuntimeException {

    public PromotionException(String message) {
        super(message);
    }
}
