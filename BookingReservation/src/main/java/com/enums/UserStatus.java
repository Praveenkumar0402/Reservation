package com.enums;

public enum UserStatus {
    ACTIVE,
    INACTIVE;

    public static UserStatus user(int value) {
        if (value == 0) return ACTIVE;
        else return INACTIVE;

    }
}
