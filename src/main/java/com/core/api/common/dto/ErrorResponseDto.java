package com.core.api.common.dto;

import com.core.api.error.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ErrorResponseDto implements Serializable {
    private final String errorCode;
    private final String reason;

    public static ErrorResponseDto of(BusinessException e) {
        String message;
        if (e.getCustomMessage() == null) {
            message = e.getErrorType().getMessage();
        } else {
            message = e.getCustomMessage();
        }

        return new ErrorResponseDto(
                e.getErrorType().name(),
                message
        );
    }
}
