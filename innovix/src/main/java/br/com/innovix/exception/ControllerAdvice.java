package br.com.innovix.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> orderNotFound() {
        ErrorDTO errorDTO = createErrorDTO("Ordem não encontrada com este id.");
        return new ResponseEntity<>(errorDTO, HttpStatusCode.valueOf(404));
    }

    private ErrorDTO createErrorDTO(String message) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);
        errorDTO.setTimeStamp(LocalDateTime.now());
        return  errorDTO;
    }
}
