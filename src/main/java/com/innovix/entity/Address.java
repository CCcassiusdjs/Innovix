package com.innovix.entity;

import javax.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String streetName;

    @NotNull
    @Column(nullable = false)
    private int number;

    @Size(max = 255)
    private String unit;

    @NotNull
    @Pattern(regexp = "\\d{5}-\\d{3}")
    @Column(nullable = false)
    private String zipCode;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String city;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String state;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(nullable = false)
    private Long personId;
}
