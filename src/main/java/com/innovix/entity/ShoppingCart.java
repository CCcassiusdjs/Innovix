package com.innovix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a shopping cart.
 * <p>
 * This class is used to map the shopping cart details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    /**
     * The unique identifier of the shopping cart.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The customer associated with the shopping cart.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Person entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Person customer;

    /**
     * The image of the product in the shopping cart.
     */
    private String productImage;

    /**
     * The name of the product in the shopping cart.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false)
    private String productName;

    /**
     * The description of the product in the shopping cart.
     * <p>
     * This field is optional and has a maximum length of 255 characters.
     * </p>
     */
    @Column(length = 255)
    private String productDescription;

    /**
     * The price of the product in the shopping cart.
     * <p>
     * This field is mandatory and must be a positive value with up to 2 decimal places.
     * </p>
     */
    @Column(nullable = false)
    private double productPrice;

    /**
     * The quantity of the product in the shopping cart.
     * <p>
     * This field is mandatory and must be a positive integer.
     * </p>
     */
    @Column(nullable = false)
    private int productQuantity;

    /**
     * The subtotal of the product in the shopping cart.
     * <p>
     * This field is mandatory and must be a positive value with up to 2 decimal places.
     * </p>
     */
    @Column(nullable = false)
    private double productSubtotal;

    /**
     * The subtotal of all products in the shopping cart.
     * <p>
     * This field is mandatory and must be a positive value with up to 2 decimal places.
     * </p>
     */
    @Column(nullable = false)
    private double subtotal;

    /**
     * The product associated with the shopping cart.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Product entity.
     * </p>
     */
     @ManyToOne
     @JoinColumn(name = "product_id", nullable = false)
     private Product product;

}