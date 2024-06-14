package br.com.innovix.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer is required")
    private Customer customer;

    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;

    @NotEmpty(message = "Origin address is required")
    private String originAddress;

    @NotEmpty(message = "Destination address is required")
    private String destinationAddress;

    @NotEmpty(message = "Products are required")
    private String products;

    @NotEmpty(message = "Payment method is required")
    private String paymentMethod;
}
