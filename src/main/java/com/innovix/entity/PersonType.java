package com.innovix.entity;

import lombok.Getter;

@Getter
public enum PersonType {
    CUSTOMER("CUSTOMER"),
    EMPLOYEE("EMPLOYEE");

    private final String value;

    PersonType(String value) {
        this.value = value;
    }
}
