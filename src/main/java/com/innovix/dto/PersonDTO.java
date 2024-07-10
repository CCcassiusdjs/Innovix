package com.innovix.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Data Transfer Object for Person entity.
 * <p>
 * This class is used to transfer person data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class PersonDTO {

    /**
     * The unique identifier of the person.
     */
    private Long id;

    /**
     * The email of the person.
     * <p>
     * This field is mandatory and must be a valid email format.
     * </p>
     */
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * The full name of the person.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotBlank(message = "Full name cannot be blank")
    private String name;

    /**
     * The CPF (Cadastro de Pessoas Físicas) of the person.
     * <p>
     * This field is mandatory and must follow the pattern XXX.XXX.XXX-XX.
     * </p>
     */
    @NotBlank(message = "CPF cannot be blank")
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "CPF must follow the pattern XXX.XXX.XXX-XX")
    private String cpf;

    /**
     * The password of the person.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotBlank(message = "Password cannot be blank")
    private String password;

    /**
     * The phone number of the person.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    /**
     * The birthday of the person.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotBlank(message = "Birthdate cannot be blank")
    private String birthdate;

    /**
     * The type of the person (CUSTOMER or EMPLOYEE).
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotBlank(message = "Type cannot be blank")
    private String type;
}
