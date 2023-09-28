package com.enums;

public enum StateOfTravel {

    BUS, TRAIN, FLIGHT;

    public static StateOfTravel getstateoftravel(String value) {
        if (value.equals("0")) return BUS;
        else if (value.equals("1")) return TRAIN;
        else if(value.equals("2")) return FLIGHT;
        return null;
    }
}