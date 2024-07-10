package com.innovix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Entity representing a payment method.
 * <p>
 * This class is used to map the payment method details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {

    /**
     * The unique identifier of the payment method.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The type of payment.
     * <p>
     * This field is mandatory and has a maximum length of 10 characters.
     * </p>
     */
    @Column(nullable = false, length = 10)
    private String paymentType;

    /**
     * The name of the cardholder.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false)
    private String cardHolder;

    /**
     * The card number.
     * <p>
     * This field is mandatory and has a fixed length of 16 characters.
     * </p>
     */
    @Column(nullable = false, length = 16)
    private String cardNumber;

    /**
     * The expiration date of the card.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private LocalDate cardExpirationLocalDate;

    /**
     * The CVV of the card.
     * <p>
     * This field is mandatory and has a fixed length of 3 characters.
     * </p>
     */
    @Column(nullable = false, length = 3)
    private String cardCvv;

    /**
     * The person associated with this payment method.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Person entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
