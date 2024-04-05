package com.core.api.error;

public class CommentLengthOutException extends BusinessException{
    public CommentLengthOutException(ErrorType errorType) {
        super(errorType);
    }
}
