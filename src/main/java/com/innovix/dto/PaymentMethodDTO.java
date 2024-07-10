package com.innovix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object for PaymentMethod entity.
 * <p>
 * This class is used to transfer payment method data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class PaymentMethodDTO {

    /**
     * The unique identifier of the payment method.
     */
    private Long paymentMethodId;

    /**
     * The type of payment.
     * <p>
     * This field is mandatory and must not exceed 10 characters.
     * </p>
     */
    @NotBlank(message = "Payment type cannot be blank")
    @Size(max = 10, message = "Payment type cannot exceed 10 characters")
    private String paymentType;

    /**
     * The name of the cardholder.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "Card holder cannot be blank")
    @Size(max = 255, message = "Card holder cannot exceed 255 characters")
    private String cardHolder;

    /**
     * The card number.
     * <p>
     * This field is mandatory and must have exactly 16 characters.
     * </p>
     */
    @NotBlank(message = "Card number cannot be blank")
    @Size(min = 16, max = 16, message = "Card number must be exactly 16 characters")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    /**
     * The expiration date of the card.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Card expiration date cannot be null")
    private LocalDate cardExpirationLocalDate;

    /**
     * The CVV of the card.
     * <p>
     * This field is mandatory and must have exactly 3 characters.
     * </p>
     */
    @NotBlank(message = "Card CVV cannot be blank")
    @Size(min = 3, max = 3, message = "Card CVV must be exactly 3 characters")
    @Pattern(regexp = "\\d{3}", message = "Card CVV must be 3 digits")
    private String cardCvv;

    /**
     * The unique identifier of the person associated with this payment method.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Person ID cannot be null")
    private Long personId;
}
