package com.innovix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a product.
 * <p>
 * This class is used to map the product details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * The unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product.
     * <p>
     * This field is mandatory and has a maximum length of 20 characters.
     * </p>
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * The description of the product.
     * <p>
     * This field is optional and has a maximum length of 255 characters.
     * </p>
     */
    @Column()
    private String description;

    /**
     * The size of the product.
     * <p>
     * This field is optional and has a maximum length of 2 characters.
     * </p>
     */
    @Column(length = 2)
    private String size;

    /**
     * The material of the product.
     * <p>
     * This field is optional and has a maximum length of 30 characters.
     * </p>
     */
    @Column(length = 30)
    private String material;

    /**
     * The dimensions of the product.
     * <p>
     * This field is embedded within the product entity.
     * </p>
     */
    @Embedded
    private Dimensions dimensions;

    /**
     * The images of the product.
     */
    private String images;

    /**
     * The price of the product.
     * <p>
     * This field is mandatory and must be a positive value with up to 2 decimal places.
     * </p>
     */
    @Column(nullable = false)
    private double price;

    /**
     * The category to which the product belongs.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Category entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * The promotion associated with the product.
     * <p>
     * This field is optional and defines a many-to-one relationship with the Promotion entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    /**
     * Embeddable class representing the dimensions of the product.
     */
    @Embeddable
    @Data
    public static class Dimensions {
        private double length;
        private double width;
        private double height;
    }
}
