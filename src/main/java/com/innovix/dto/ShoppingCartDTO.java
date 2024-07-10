package com.innovix.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Data Transfer Object for ShoppingCart entity.
 * <p>
 * This class is used to transfer shopping cart data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class ShoppingCartDTO {

    /**
     * The unique identifier of the shopping cart.
     */
    private Long shoppingCartId;

    /**
     * The unique identifier of the customer associated with the shopping cart.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    /**
     * The image of the product in the shopping cart.
     */
    private String productImage;

    /**
     * The name of the product in the shopping cart.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    private String productName;

    /**
     * The description of the product in the shopping cart.
     * <p>
     * This field is optional and must not exceed 255 characters.
     * </p>
     */
    @Size(max = 255, message = "Product description cannot exceed 255 characters")
    private String productDescription;

    /**
     * The price of the product in the shopping cart.
     * <p>
     * This field is mandatory and must be a positive value with up to 2 decimal places.
     * </p>
     */
    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be positive")
    @Digits(integer = 10, fraction = 2, message = "Product price must be a valid monetary amount")
    private double productPrice;

    /**
     * The quantity of the product in the shopping cart.
     * <p>
     * This field is mandatory and must be a positive integer.
     * </p>
     */
    @NotNull(message = "Product quantity cannot be null")
    @Positive(message = "Product quantity must be positive")
    private int productQuantity;

    /**
     * The subtotal of the product in the shopping cart.
     * <p>
     * This field is mandatory and must be a positive value with up to 2 decimal places.
     * </p>
     */
    @NotNull(message = "Product subtotal cannot be null")
    @Positive(message = "Product subtotal must be positive")
    @Digits(integer = 10, fraction = 2, message = "Product subtotal must be a valid monetary amount")
    private double productSubtotal;

    /**
     * The subtotal of all products in the shopping cart.
     * <p>
     * This field is mandatory and must be a positive value with up to 2 decimal places.
     * </p>
     */
    @NotNull(message = "Subtotal cannot be null")
    @Positive(message = "Subtotal must be positive")
    @Digits(integer = 10, fraction = 2, message = "Subtotal must be a valid monetary amount")
    private double subtotal;

    /**
     * The unique identifier of the product associated with the shopping cart.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Product ID cannot be null")
    private Long productId;
    private double icms;
}
