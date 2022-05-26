package com.talkon.talkon.exceptions;

public class MentorIdNotFoundException extends ValidationException {
    public MentorIdNotFoundException(String message) {
        super(message);
    }

    public MentorIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
