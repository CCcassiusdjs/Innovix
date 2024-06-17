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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date orderDate;

    @NotNull
    @Size(max = 20)
    @Column(nullable = false)
    private String orderStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Person customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_origin_id", nullable = false)
    private Address addressOrigin;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_destination_id", nullable = false)
    private Address addressDestination;

    private String productImage;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String productName;

    @Size(max = 255)
    private String productDescription;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private Double productPrice;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer productQuantity;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private Double productSubtotal;

    private Integer freeQuantity; // NEW: Free quantity given in the promotion

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
