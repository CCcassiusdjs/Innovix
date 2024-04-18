package br.com.innovix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Classe externa que contém todas as exceções
public class Exceptions {
    /**
     * Base class for all resource not found exceptions.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Base class for data validation failures.
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public static class DataValidationException extends RuntimeException {
        public DataValidationException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when an employee cannot be found.
     */
    public static class EmployeeNotFoundException extends ResourceNotFoundException {
        public EmployeeNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when a customer cannot be found.
     */
    public static class CustomerNotFoundException extends ResourceNotFoundException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when an inventory item is not available or does not exist.
     */
    public static class InventoryException extends ResourceNotFoundException {
        public InventoryException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when an order cannot be found.
     */
    public static class OrderNotFoundException extends ResourceNotFoundException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when a product cannot be found.
     */
    public static class ProductNotFoundException extends ResourceNotFoundException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when there is an issue with a promotion, such as invalid data or configuration.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class PromotionException extends DataValidationException {
        public PromotionException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when a sale item cannot be found.
     */
    public static class SaleItemNotFoundException extends ResourceNotFoundException {
        public SaleItemNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when an item in an order cannot be found.
     */
    public static class ItemInOrderNotFoundException extends ResourceNotFoundException {
        public ItemInOrderNotFoundException(String message) {
            super(message);
        }
    }

}
