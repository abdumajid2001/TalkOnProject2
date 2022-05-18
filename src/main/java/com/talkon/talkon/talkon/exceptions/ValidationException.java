package com.talkon.talkon.talkon.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
public class ValidationException extends RuntimeException {

    private Timestamp timestamp;
    private String developerMessage;
    private String path;

    public ValidationException(String message) {
        super(message);
        this.timestamp = new Timestamp(new Date().getTime());
        this.developerMessage = Arrays.toString(super.getStackTrace());
    }
    public ValidationException(String message, Throwable cause) {
        super(message,cause);
        this.timestamp = new Timestamp(new Date().getTime());
        this.developerMessage = Arrays.toString(super.getStackTrace());

    }
}
