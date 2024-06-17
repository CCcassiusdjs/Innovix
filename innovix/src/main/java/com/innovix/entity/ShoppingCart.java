package com.innovix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingCartId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Person customer;

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

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
