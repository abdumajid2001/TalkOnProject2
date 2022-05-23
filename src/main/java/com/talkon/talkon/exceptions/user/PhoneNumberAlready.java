package com.talkon.talkon.exceptions.user;

import com.talkon.talkon.exceptions.ValidationException;

public class PhoneNumberAlready extends ValidationException{
    public PhoneNumberAlready(String message) {
        super(message);
    }

    public PhoneNumberAlready(String message, Throwable cause) {
        super(message, cause);
    }
}
