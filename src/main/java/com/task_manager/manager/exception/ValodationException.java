package com.task_manager.manager.exception;

public class ValodationException extends Exception {
    public ValodationException() {
    }

    public ValodationException(String message) {
        super(message);
    }

    public ValodationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValodationException(Throwable cause) {
        super(cause);
    }

}
