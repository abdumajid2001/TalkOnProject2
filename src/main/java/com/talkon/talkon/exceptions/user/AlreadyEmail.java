package com.talkon.talkon.exceptions.user;

import com.talkon.talkon.exceptions.ValidationException;

public class AlreadyEmail extends ValidationException {
    public AlreadyEmail(String message) {
        super(message);
    }

    public AlreadyEmail(String message, Throwable cause) {
        super(message, cause);
    }
}
