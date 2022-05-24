package com.talkon.talkon.exceptions.user;

import com.talkon.talkon.exceptions.ValidationException;

public class MentorNotFoundException extends ValidationException {
    public MentorNotFoundException(String message) {
        super(message);
    }

    public MentorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
