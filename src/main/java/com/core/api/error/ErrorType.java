package com.core.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 에러가 발생했습니다."),
    INVALID_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    /**
     * user
     */
    ALREADY_EXISTS_USER_ERROR(HttpStatus.BAD_REQUEST, "이미 존재하는 유저입니다."),
    USER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    COMMENT_MESSAGE_LENGTH_ERROR(HttpStatus.BAD_REQUEST, "댓글은 1자 이상 50자 이하로 작성해주세요."),

    /**
     * item
     */
    NOT_FOUND_ITEM_ERROR(HttpStatus.NOT_FOUND, "아이템 정보를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
