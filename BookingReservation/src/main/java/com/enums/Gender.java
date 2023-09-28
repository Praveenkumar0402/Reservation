package com.enums;

public enum Gender {
    MALE,
    FEMALE,
    OTHERS;

    public static Gender getGender(String value) {
        if (value.equals("0")) return MALE;
        else if (value.equals("1")) return FEMALE;
        else return OTHERS;

    }
}
