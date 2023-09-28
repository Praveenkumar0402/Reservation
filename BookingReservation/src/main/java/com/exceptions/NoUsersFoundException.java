package com.exceptions;

public class NoUsersFoundException extends RuntimeException {
    public NoUsersFoundException(String msg) {
        super(msg);
    }

}
