package com.innovix.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_cart")
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
    @Positive
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private double productPrice;

    @NotNull
    @Positive
    @Column(nullable = false)
    private int productQuantity;

    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private double productSubtotal;

    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
