package br.com.innovix.exception;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    String message;
    private LocalDateTime timeStamp;
}