package com.rmtest.library.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;

}
