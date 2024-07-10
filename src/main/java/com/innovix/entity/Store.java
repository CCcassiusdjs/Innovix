package com.innovix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a store.
 * <p>
 * This class is used to map the store details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    /**
     * The unique identifier of the store.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the store.
     * <p>
     * This field is mandatory and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false)
    private String storeName;

    /**
     * The CNPJ of the store.
     * <p>
     * This field is mandatory and has a maximum length of 20 characters.
     * </p>
     */
    @Column(nullable = false, length = 20)
    private String storeCnpj;

    /**
     * The address associated with the store.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Address entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    /**
     * The employee associated with the store.
     * <p>
     * This field is mandatory and defines a many-to-one relationship with the Person entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Person employee;
}
