package com.innovix.entity;

import lombok.Getter;

/**
 * Enum representing the type of a person.
 * <p>
 * This enum is used to distinguish between different types of persons (CUSTOMER or EMPLOYEE).
 * </p>
 */
@Getter
public enum PersonType {
    CUSTOMER("CUSTOMER"),
    EMPLOYEE("EMPLOYEE");

    private final String value;

    PersonType(String value) {
        this.value = value;
    }
}
