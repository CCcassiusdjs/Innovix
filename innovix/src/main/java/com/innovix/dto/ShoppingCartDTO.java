package com.innovix.dto;

import lombok.Data;

/**
 * Data Transfer Object for the ShoppingCart entity.
 */
@Data
public class ShoppingCartDTO {
    private Long shoppingCartId;
    private Long customerId;
    private String productImage;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
    private Double productSubtotal;
    private Double subtotal;
    private Long productId;
}
