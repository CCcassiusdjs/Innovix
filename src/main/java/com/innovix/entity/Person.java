package com.innovix.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Entity representing a person.
 * <p>
 * This class is used to map the person details to the database.
 * It includes JPA annotations to define the database constraints and relationships.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "person")
public class Person implements UserDetails {

    /**
     * The unique identifier of the person.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The email of the person.
     * <p>
     * This field is mandatory and unique.
     * </p>
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The full name of the person.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * The CPF (Cadastro de Pessoas Físicas) of the person.
     * <p>
     * This field is mandatory and unique.
     * </p>
     */
    @Column(nullable = false, unique = true)
    private String cpf;

    /**
     * The password of the person.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private String password;

    /**
     * The phone number of the person.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private String phone;

    /**
     * The birthday of the person.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private LocalDate birthday;

    /**
     * The type of the person (CUSTOMER or EMPLOYEE).
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PersonType type;

    /**
     * Constructs a new {@code Person} with the specified ID.
     *
     * @param id the unique identifier of the person
     */
    public Person(Long id) {
        this.id = id;
    }

    /**
     * Returns the authorities granted to the person.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(type.getValue()));
    }

    /**
     * Returns the username used to authenticate the person.
     *
     * @return the email of the person
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the person's account has expired.
     *
     * @return {@code true} if the person's account is non-expired, {@code false} otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the person is locked or unlocked.
     *
     * @return {@code true} if the person is non-locked, {@code false} otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the person's credentials (password) have expired.
     *
     * @return {@code true} if the credentials are non-expired, {@code false} otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the person is enabled or disabled.
     *
     * @return {@code true} if the person is enabled, {@code false} otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
