package com.innovix.dto;

import lombok.Data;

import java.util.Date;

/**
 * Data Transfer Object for the Person entity.
 */
@Data
public class PersonDTO {
    private Long personId;
    private String name;
    private Date birthDate;
    private String email;
    private String cpf;
    private String phone;
    private Date registerDate;
    private String type;
}
