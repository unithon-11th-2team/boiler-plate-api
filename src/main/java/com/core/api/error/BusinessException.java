package com.core.api.error;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorType errorType;
    private String customMessage;
    private String[] args;
    private Object data;

    public BusinessException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public BusinessException(ErrorType errorType, String customMessage) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.customMessage = customMessage;
    }

    public BusinessException(ErrorType errorType, Object data, String... args) {
        super(errorType.getMessage());
        this.data = data;
        this.args = args;
        this.errorType = errorType;
    }

    public BusinessException(ErrorType errorType, Throwable t) {
        super(t);
        this.errorType = errorType;
    }

    public BusinessException(ErrorType errorType, Throwable t, String customMessage) {
        super(t);
        this.errorType = errorType;
        this.customMessage = customMessage;
    }
}
