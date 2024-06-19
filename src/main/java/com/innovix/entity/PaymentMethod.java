package com.innovix.entity;

import javax.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private LocalDate cardExpirationLocalDate;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(nullable = false)
    private String cardCvv;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
