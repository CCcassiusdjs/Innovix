package com.innovix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity representing a promotion.
 * <p>
 * This class is used to map the promotion details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    /**
     * The unique identifier of the promotion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The description of the promotion.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false, length = 50)
    private String description;

    /**
     * The season of the promotion.
     * <p>
     * This field is mandatory and has a maximum length of 6 characters.
     * </p>
     */
    @Column(nullable = false, length = 10)
    private String season;

    /**
     * The start date of the promotion.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private LocalDate initLocalDate;

    /**
     * The end date of the promotion.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private LocalDate endLocalDate;

    /**
     * The duration of the promotion.
     */
    private int duration;

    /**
     * The percentage discount of the promotion.
     * <p>
     * This field must be a positive value.
     * </p>
     */
    @Column
    private double percentage;

    /**
     * The required quantity for the promotion.
     */
    @Column
    private int requiredQuantity;

    /**
     * The free quantity given in the promotion.
     */
    @Column
    private int freeQuantity;

    /**
     * The employee associated with the promotion.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Person entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Person employee;
}
