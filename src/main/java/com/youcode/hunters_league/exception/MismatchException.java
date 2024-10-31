package com.youcode.hunters_league.exception;

public class MismatchException extends RuntimeException {
    private final String field;
    private final String message;

    public MismatchException(String field, String message) {
        super(message);
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
