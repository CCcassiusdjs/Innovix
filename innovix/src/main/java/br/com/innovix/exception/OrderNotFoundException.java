package br.com.innovix.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final String message = "Ordem não encontrada com este id.";

    public OrderNotFoundException() {
        super(message);
    }
}
