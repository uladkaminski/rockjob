package com.oyper.rockjob.exception;

public class NoSuchJobException extends RuntimeException {

    public NoSuchJobException() {
    }

    public NoSuchJobException(String message) {
        super(message);
    }

    public NoSuchJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchJobException(Throwable cause) {
        super(cause);
    }

    public NoSuchJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
