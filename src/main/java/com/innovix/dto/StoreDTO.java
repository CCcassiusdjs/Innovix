package com.innovix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for Store entity.
 * <p>
 * This class is used to transfer store data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class StoreDTO {

    /**
     * The unique identifier of the store.
     */
    private Long storeId;

    /**
     * The name of the store.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "Store name cannot be blank")
    @Size(max = 255, message = "Store name cannot exceed 255 characters")
    private String storeName;

    /**
     * The CNPJ of the store.
     * <p>
     * This field is mandatory and must not exceed 20 characters.
     * </p>
     */
    @NotBlank(message = "Store CNPJ cannot be blank")
    @Size(max = 20, message = "Store CNPJ cannot exceed 20 characters")
    private String storeCnpj;

    /**
     * The unique identifier of the address associated with the store.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Address ID cannot be null")
    private Long addressId;

    /**
     * The unique identifier of the employee associated with the store.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;
}
