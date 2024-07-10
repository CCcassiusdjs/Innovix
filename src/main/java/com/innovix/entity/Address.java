package com.innovix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Entity representing an address.
 * <p>
 * This class is used to map the address details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    /**
     * The unique identifier of the address.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the street.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false)
    private String streetName;

    /**
     * The number of the address.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private int number;

    /**
     * The unit or apartment number.
     * <p>
     * This field is optional and has a maximum length of 255 characters.
     * </p>
     */
    @Column()
    private String unit;

    /**
     * The zip code of the address.
     * <p>
     * This field is mandatory and has a maximum length of 10 characters.
     * </p>
     */
    @Column(nullable = false, length = 10)
    private String zipCode;

    /**
     * The city of the address.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false)
    private String city;

    /**
     * The state of the address.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false)
    private String state;

    /**
     * The country of the address.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false)
    private String country;

    /**
     * The unique identifier of the person associated with this address.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private Long personId;
}
