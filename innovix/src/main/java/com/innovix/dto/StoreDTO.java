package com.innovix.dto;

import lombok.Data;

/**
 * Data Transfer Object for the Store entity.
 */
@Data
public class StoreDTO {
    private Long storeId;
    private String storeName;
    private String storeCnpj;
    private Long addressId;
    private Long employeeId;
}
