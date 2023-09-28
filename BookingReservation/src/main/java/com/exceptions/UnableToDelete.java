package com.exceptions;

public class UnableToDelete extends RuntimeException {
    public UnableToDelete(String msg) {
        super(msg);
    }

}
