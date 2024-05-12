package br.com.innovix.domain.user;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
    CLIENT("CLIENT"),
    EMPLOYEE("EMPLOYEE");

    private final String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

}
