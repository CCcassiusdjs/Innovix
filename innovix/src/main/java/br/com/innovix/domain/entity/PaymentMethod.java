package br.com.innovix.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentMethodId;

    @NotEmpty(message = "Type is required")
    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer is required")
    private Customer customer;
}
