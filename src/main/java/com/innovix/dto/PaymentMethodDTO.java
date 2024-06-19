package com.innovix.dto;

import lombok.Data;

import java.util.Date;

/**
 * Data Transfer Object for the PaymentMethod entity.
 */
@Data
public class PaymentMethodDTO {
    private Long paymentMethodId;
    private String paymentType;
    private String cardHolder;
    private String cardNumber;
    private Date cardExpirationDate;
    private String cardCvv;
    private Long personId;
}
