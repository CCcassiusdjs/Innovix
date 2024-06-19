package com.innovix.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object for the PaymentMethod entity.
 */
@Data
public class PaymentMethodDTO {
    private Long paymentMethodId;
    private String paymentType;
    private String cardHolder;
    private String cardNumber;
    private LocalDate cardExpirationLocalDate;
    private String cardCvv;
    private Long personId;
}
