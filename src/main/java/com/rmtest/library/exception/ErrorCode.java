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
    BOOK_INFO_NOT_FOUND("도서가 없습니다.", NOT_FOUND),

    /* Rent */
    UNMATCHED_USER("본인의 계정으로만 사용가능합니다.", BAD_REQUEST),
    OUT_OF_STOCK("대여가능한 도서 재고가 없습니다.", BAD_REQUEST),
    RENT_NOT_FOUND("대여정보가 없습니다.", NOT_FOUND),
    OVERDUE_BOOK("반납기한이 지난 도서입니다.", BAD_REQUEST);


    private final String description;
    private final HttpStatus status;

    ErrorCode(String description, HttpStatus status) {
        this.description = description;
        this.status = status;
    }
}
