package com.notemates.rockjob.exception;

public class RockJobException extends RuntimeException {
    public RockJobException() {
    }

    public RockJobException(String message) {
        super(message);
    }

    public RockJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public RockJobException(Throwable cause) {
        super(cause);
    }

    public RockJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
