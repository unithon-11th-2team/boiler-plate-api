package com.core.api.error;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorType errorType) {
        super(errorType);
    }
}
