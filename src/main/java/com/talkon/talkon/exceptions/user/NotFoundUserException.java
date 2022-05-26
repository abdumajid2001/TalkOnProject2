package com.talkon.talkon.exceptions.user;

import com.talkon.talkon.exceptions.ValidationException;

public class NotFoundUserException extends ValidationException {
    public NotFoundUserException(String message) {
        super(message);
    }

    public NotFoundUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
