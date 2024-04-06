package com.core.api.error;

public class InvalidRequestException extends BusinessException {
    public InvalidRequestException(ErrorType errorType) {
        super(errorType);
    }
}
