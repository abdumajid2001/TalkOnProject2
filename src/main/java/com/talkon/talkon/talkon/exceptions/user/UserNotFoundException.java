package com.talkon.talkon.talkon.exceptions.user;

import com.talkon.talkon.talkon.exceptions.ValidationException;

public class UserNotFoundException extends ValidationException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
