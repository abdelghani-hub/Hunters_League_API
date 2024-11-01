package com.youcode.hunters_league.exception;

public class NullOrBlankArgException extends RuntimeException {
    public NullOrBlankArgException(String arg) {
        super(arg + " cannot be null or blank");
    }
}
