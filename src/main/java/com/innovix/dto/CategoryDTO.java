package com.innovix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for Category entity.
 * <p>
 * This class is used to transfer category data between different layers of the application.
 * It includes validation annotations to ensure the integrity and correctness of the data.
 * </p>
 */
@Data
public class CategoryDTO {

    /**
     * The unique identifier of the category.
     */
    private Long categoryId;

    /**
     * The name of the category.
     * <p>
     * This field is mandatory and must not exceed 255 characters.
     * </p>
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;
}
