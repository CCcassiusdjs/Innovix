package com.innovix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity representing a purchase order.
 * <p>
 * This class is used to map the purchase order details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    /**
     * The unique identifier of the purchase order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The date when the order was placed.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private LocalDate orderLocalDate;

    /**
     * The status of the order.
     * <p>
     * This field is mandatory and has a maximum length of 20 characters.
     * </p>
     */
    @Column(nullable = false, length = 20)
    private String orderStatus;

    /**
     * The customer who placed the order.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Person entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Person customer;

    /**
     * The origin address of the order.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Address entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "address_origin_id", nullable = false)
    private Address addressOrigin;

    /**
     * The destination address of the order.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Address entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "address_destination_id", nullable = false)
    private Address addressDestination;

    /**
     * The image of the product.
     */
    private String productImage;

    /**
     * The name of the product.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false)
    private String productName;

    /**
     * The description of the product.
     * <p>
     * This field is optional and has a maximum length of 255 characters.
     * </p>
     */
    @Column(length = 255)
    private String productDescription;

    /**
     * The price of the product.
     * <p>
     * This field is mandatory and must be a positive value.
     * </p>
     */
    @Column(nullable = false, precision = 12)
    private BigDecimal productPrice;

    /**
     * The quantity of the product ordered.
     * <p>
     * This field is mandatory and must be a positive integer.
     * </p>
     */
    @Column(nullable = false)
    private int productQuantity;

    /**
     * The subtotal of the product order.
     * <p>
     * This field is mandatory and must be a positive value.
     * </p>
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal productSubtotal;

    /**
     * The quantity of free items included in the order.
     */
    private int freeQuantity;

    /**
     * The product associated with this order.
     * <p>
     * This field defines a many-to-one relationship with the Product entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
