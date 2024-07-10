package com.innovix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for Address entity.
 * <p>
 * This class is used to transfer address data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class AddressDTO {

    /**
     * The unique identifier of the address.
     */
    private Long addressId;

    /**
     * The name of the street.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "Street name cannot be blank")
    @Size(max = 255, message = "Street name cannot exceed 255 characters")
    private String streetName;

    /**
     * The number of the address.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Number cannot be null")
    private Integer number;

    /**
     * The unit or apartment number.
     * <p>
     * This field is optional and must not exceed 255 characters.
     * </p>
     */
    @Size(max = 255, message = "Unit cannot exceed 255 characters")
    private String unit;

    /**
     * The zip code of the address.
     * <p>
     * This field is mandatory and must follow the pattern 12345-678.
     * </p>
     */
    @NotBlank(message = "Zip code cannot be blank")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Zip code must follow the pattern 12345-678")
    private String zipCode;

    /**
     * The city of the address.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "City cannot be blank")
    @Size(max = 255, message = "City cannot exceed 255 characters")
    private String city;

    /**
     * The state of the address.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "State cannot be blank")
    @Size(max = 255, message = "State cannot exceed 255 characters")
    private String state;

    /**
     * The country of the address.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 255, message = "Country cannot exceed 255 characters")
    private String country;

    /**
     * The unique identifier of the person associated with this address.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Person ID cannot be null")
    private Long personId;
}
