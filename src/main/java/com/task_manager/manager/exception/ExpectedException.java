package com.task_manager.manager.exception;

public class ExpectedException extends Exception {
    public ExpectedException() {
    }

    public ExpectedException(String message) {
        super(message);
    }

    public ExpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpectedException(Throwable cause) {
        super(cause);
    }
}
