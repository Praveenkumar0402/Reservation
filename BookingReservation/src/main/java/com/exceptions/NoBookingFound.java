package com.exceptions;

public class NoBookingFound extends RuntimeException {
    public NoBookingFound(String msg) {
        super(msg);
    }

}
