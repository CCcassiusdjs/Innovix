package br.com.innovix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Classe externa que contém todas as exceções
public class Exceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class EmployeeNotFoundException extends NotFoundException {
        public EmployeeNotFoundException(String message) {
            super(message);
        }
    }

    public static class CustomerNotFoundException extends NotFoundException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class InventoryException extends NotFoundException {
        public InventoryException(String message) {
            super(message);
        }
    }

    public static class OrderNotFoundException extends NotFoundException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProductNotFoundException extends NotFoundException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class PromotionException extends NotFoundException {
        public PromotionException(String message) {
            super(message);
        }
    }

    public static class SaleItemNotFoundException extends NotFoundException {
        public SaleItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class TaxException extends NotFoundException {
        public TaxException(String message) {
            super(message);
        }
    }

    public static class ItemInOrderNotFoundException extends NotFoundException {
        public ItemInOrderNotFoundException(String message) {
            super(message);
        }
    }
}
