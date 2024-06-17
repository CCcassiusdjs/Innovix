package com.innovix.dto;

import lombok.Data;

import java.util.Date;

/**
 * Data Transfer Object for the Order entity.
 */
@Data
public class OrderDTO {
    private Long orderId;
    private Date orderDate;
    private String orderStatus;
    private Long customerId;
    private Long addressOriginId;
    private Long addressDestinationId;
    private String productImage;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
    private Double productSubtotal;
    private Long productId;
}
