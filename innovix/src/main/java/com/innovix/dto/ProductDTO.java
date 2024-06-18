package com.innovix.dto;

import lombok.Data;

/**
 * Data Transfer Object for the Product entity.
 */
@Data
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private String size;
    private String material;
    private DimensionsDTO dimensions;
    private String images;
    private double price;
    private Long categoryId;
    private Long promotionId;

    @Data
    public static class DimensionsDTO {
        private double length;
        private double width;
        private double height;
    }
}
