package com.osetskiy.baby_shop.rest.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class AccountExistException extends RuntimeException {
    public AccountExistException() {
    }

    public AccountExistException(Throwable cause) {
        super(cause);
    }
}
