package com.innovix.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object for the Order entity.
 */
@Data
public class OrderDTO {
    private Long orderId;
    private LocalDate orderLocalDate;
    private String orderStatus;
    private Long customerId;
    private Long addressOriginId;
    private Long addressDestinationId;
    private String productImage;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private double productSubtotal;
    private int freeQuantity;
    private Long productId;
}
