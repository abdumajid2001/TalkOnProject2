package com.talkon.talkon.exceptions.user;

import com.talkon.talkon.exceptions.ValidationException;

public class NotFoundUserIdException extends ValidationException {

    public NotFoundUserIdException(String message) {
        super(message);
    }

    public NotFoundUserIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
