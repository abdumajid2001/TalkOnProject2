package com.talkon.talkon.exceptions.user;

import com.talkon.talkon.exceptions.ValidationException;

public class AlreadyUsername extends ValidationException {
    public AlreadyUsername(String message) {
        super(message);
    }

    public AlreadyUsername(String message, Throwable cause) {
        super(message, cause);
    }
}
