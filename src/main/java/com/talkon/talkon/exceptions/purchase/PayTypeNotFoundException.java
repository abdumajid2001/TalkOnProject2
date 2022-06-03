package com.talkon.talkon.exceptions.purchase;

import com.talkon.talkon.exceptions.ValidationException;

public class PayTypeNotFoundException extends ValidationException {
    public PayTypeNotFoundException(String format) {
        super(format);
    }
}
