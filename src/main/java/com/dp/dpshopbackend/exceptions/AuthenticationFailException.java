package com.dp.dpshopbackend.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {

    public AuthenticationFailException(String msg) {
        super(msg);
    }

}
