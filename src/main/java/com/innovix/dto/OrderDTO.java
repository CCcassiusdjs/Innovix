package com.innovix.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object for PurchaseOrder entity.
 * <p>
 * This class is used to transfer order data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class OrderDTO {

    /**
     * The unique identifier of the order.
     */
    private Long orderId;

    /**
     * The date when the order was placed.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Order date cannot be null")
    private LocalDate orderLocalDate;

    /**
     * The status of the order.
     * <p>
     * This field is mandatory and must not exceed 20 characters.
     * </p>
     */
    @NotBlank(message = "Order status cannot be blank")
    @Size(max = 20, message = "Order status cannot exceed 20 characters")
    private String orderStatus;

    /**
     * The unique identifier of the customer who placed the order.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    /**
     * The unique identifier of the origin address.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Address origin ID cannot be null")
    private Long addressOriginId;

    /**
     * The unique identifier of the destination address.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Address destination ID cannot be null")
    private Long addressDestinationId;

    /**
     * The image of the product.
     */
    private String productImage;

    /**
     * The name of the product.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    private String productName;

    /**
     * The description of the product.
     * <p>
     * This field is optional and must not exceed 255 characters.
     * </p>
     */
    @Size(max = 255, message = "Product description cannot exceed 255 characters")
    private String productDescription;

    /**
     * The price of the product.
     * <p>
     * This field is mandatory and must be a positive value.
     * </p>
     */
    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be positive")
    private double productPrice;

    /**
     * The quantity of the product ordered.
     * <p>
     * This field is mandatory and must be a positive integer.
     * </p>
     */
    @NotNull(message = "Product quantity cannot be null")
    @Positive(message = "Product quantity must be positive")
    private int productQuantity;

    /**
     * The subtotal of the product order.
     * <p>
     * This field is mandatory and must be a positive value.
     * </p>
     */
    @NotNull(message = "Product subtotal cannot be null")
    @Positive(message = "Product subtotal must be positive")
    private double productSubtotal;

    /**
     * The quantity of free items included in the order.
     */
    @PositiveOrZero(message = "Free quantity must be zero or positive")
    private int freeQuantity;

    /**
     * The unique identifier of the product associated with this order.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Product ID cannot be null")
    private Long productId;
}
