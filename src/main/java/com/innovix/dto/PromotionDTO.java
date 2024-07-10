package com.innovix.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Data Transfer Object for Promotion entity.
 * <p>
 * This class is used to transfer promotion data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class PromotionDTO {

    /**
     * The unique identifier of the promotion.
     */
    private Long promotionId;

    /**
     * The description of the promotion.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 50, message = "Description cannot exceed 50 characters")
    private String description;

    /**
     * The season of the promotion.
     * <p>
     * This field is mandatory and must not exceed 6 characters.
     * </p>
     */
    @NotBlank(message = "Season cannot be blank")
    @Size(max = 10, message = "Season cannot exceed 10 characters")
    private String season;

    /**
     * The start date of the promotion.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Start date cannot be null")
    private LocalDate initLocalDate;

    /**
     * The end date of the promotion.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "End date cannot be null")
    private LocalDate endLocalDate;

    /**
     * The duration of the promotion.
     */
    private int duration;

    /**
     * The percentage discount of the promotion.
     * <p>
     * This field must be a positive value.
     * </p>
     */
    @Positive(message = "Percentage must be positive")
    private double percentage;

    /**
     * The required quantity for the promotion.
     * <p>
     * This field must be at least 1.
     * </p>
     */
    @Min(value = 1, message = "Required quantity must be at least 1")
    private int requiredQuantity;

    /**
     * The free quantity given in the promotion.
     * <p>
     * This field must be at least 0.
     * </p>
     */
    @Min(value = 0, message = "Free quantity must be at least 0")
    private int freeQuantity;

    /**
     * The unique identifier of the employee associated with the promotion.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;
}
