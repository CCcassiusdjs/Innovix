package com.innovix.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long addressId;
    private String streetName;
    private int number;
    private String unit;
    private String zipCode;
    private String city;
    private String state;
    private String country;
    private Long personId;
}
