package com.innovix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a category.
 * <p>
 * This class is used to map the category details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    /**
     * The unique identifier of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the category.
     * <p>
     * This field is mandatory, unique, and has a maximum length of 255 characters.
     * </p>
     */
    @Column(nullable = false, unique = true)
    private String name;
}
