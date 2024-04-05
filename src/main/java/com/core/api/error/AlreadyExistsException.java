package com.core.api.error;

public class AlreadyExistsException extends BusinessException {
    public AlreadyExistsException(ErrorType errorType) {
        super(errorType);
    }
}
