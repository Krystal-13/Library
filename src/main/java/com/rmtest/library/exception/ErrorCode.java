package com.rmtest.library.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {

    /* User */
    USER_NOT_FOUND("사용자가 없습니다.", NOT_FOUND),
    ALREADY_REGISTERED_USER("이미 존재하는 사용자입니다.", BAD_REQUEST),
    UNMATCHED_INFORMATION("아이디 또는 비밀번호가 일치하지 않습니다.", BAD_REQUEST),

    /* Auth */
    INVALID_REQUEST("잘못된 요청입니다.", BAD_REQUEST),
    EXPIRED_TOKEN("인증 토큰이 만료되었습니다.", UNAUTHORIZED),

    /* Book */
    ACCESS_DENIED("접근권한이 없습니다.", FORBIDDEN),
    ALREADY_EXIST_BOOK("이미 등록된 도서입니다.", BAD_REQUEST),
    BOOK_NOT_FOUND("도서가 없습니다.", NOT_FOUND);


    private final String description;
    private final HttpStatus status;

    ErrorCode(String description, HttpStatus status) {
        this.description = description;
        this.status = status;
    }
}
