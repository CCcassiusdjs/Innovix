package com.innovix.entity;

import javax.validation.constraints.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 11, max = 11)
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotNull
    @Size(max = 20)
    @Column(nullable = false)
    private String phone;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date registerDate;

    @NotNull
    @Size(max = 8)
    @Column(nullable = false)
    private String type;

    public Person(Long id) {
        this.id = id;
    }
}
