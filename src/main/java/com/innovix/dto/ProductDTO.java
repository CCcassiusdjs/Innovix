package com.innovix.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Data Transfer Object for Product entity.
 * <p>
 * This class is used to transfer product data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class ProductDTO {

    /**
     * The unique identifier of the product.
     */
    private Long productId;

    /**
     * The name of the product.
     * <p>
     * This field is mandatory and must not exceed 20 characters.
     * </p>
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    /**
     * The description of the product.
     * <p>
     * This field is optional and must not exceed 255 characters.
     * </p>
     */
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    /**
     * The size of the product.
     * <p>
     * This field is optional and must not exceed 2 characters.
     * </p>
     */
    @Size(max = 2, message = "Size cannot exceed 2 characters")
    private String size;

    /**
     * The material of the product.
     * <p>
     * This field is optional and must not exceed 30 characters.
     * </p>
     */
    @Size(max = 30, message = "Material cannot exceed 30 characters")
    private String material;

    /**
     * The dimensions of the product.
     */
    @NotNull(message = "Dimensions cannot be null")
    private DimensionsDTO dimensions;

    /**
     * The images of the product.
     */
    private String images;

    /**
     * The price of the product.
     * <p>
     * This field is mandatory and must be a positive value with up to 2 decimal places.
     * </p>
     */
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount")
    private double price;

    /**
     * The unique identifier of the category to which the product belongs.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    /**
     * The unique identifier of the promotion associated with the product.
     * <p>
     * This field is optional.
     * </p>
     */
    private Long promotionId;

    /**
     * Data Transfer Object for Dimensions embedded class.
     */
    @Data
    public static class DimensionsDTO {
        /**
         * The length of the product.
         */
        @Positive(message = "Length must be positive")
        private double length;

        /**
         * The width of the product.
         */
        @Positive(message = "Width must be positive")
        private double width;

        /**
         * The height of the product.
         */
        @Positive(message = "Height must be positive")
        private double height;
    }
}
