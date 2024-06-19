package com.innovix.entity;

import javax.validation.constraints.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(nullable = false, precision = 12, scale = 2)
    @Positive
    private BigDecimal productPrice;

    @NotNull
    @Positive
    @Column(nullable = false)
    private int productQuantity;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal productSubtotal;

    private int freeQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
