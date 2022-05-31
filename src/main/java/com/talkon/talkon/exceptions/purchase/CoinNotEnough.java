package com.talkon.talkon.exceptions.purchase;

import com.talkon.talkon.exceptions.ValidationException;

public class CoinNotEnough extends ValidationException {
    public CoinNotEnough(String format) {
        super(format);
    }
}
