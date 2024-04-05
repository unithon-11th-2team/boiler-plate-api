package com.core.api.error;

import com.core.api.common.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleException(BusinessException e) {
        log.error("[ERROR] BusinessException -> {}", e.getMessage());

        return ResponseEntity
                .status(e.getErrorType().getStatus())
                .body(ErrorResponseDto.of(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.error("[ERROR] Exception -> {}", e.getMessage());

        var type = ErrorType.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(type.getStatus())
                .body(new ErrorResponseDto(type.name(), e.getMessage()));
    }
}
