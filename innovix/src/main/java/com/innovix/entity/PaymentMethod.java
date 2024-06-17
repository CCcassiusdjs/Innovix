package com.innovix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentMethodId;

    @NotNull
    @Size(max = 10)
    @Column(nullable = false)
    private String paymentType;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String cardHolder;

    @NotNull
    @Size(min = 16, max = 16)
    @Column(nullable = false)
    private String cardNumber;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date cardExpirationDate;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(nullable = false)
    private String cardCvv;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
