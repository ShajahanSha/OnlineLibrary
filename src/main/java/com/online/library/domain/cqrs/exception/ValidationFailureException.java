package com.online.library.domain.cqrs.exception;

public class ValidationFailureException extends Exception {

    private static final long serialVersionUID = 1L;

    private ErrorResponse errorResponse;

    public ValidationFailureException() {
        super();
    }

    public ValidationFailureException(String message) {
        super(message);
    }

    public ValidationFailureException(Exception exception, String message) {
        super(message, exception);
    }

    public ValidationFailureException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}

