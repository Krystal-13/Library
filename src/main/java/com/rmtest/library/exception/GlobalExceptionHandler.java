package com.rmtest.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException e) {

        log.error("{} is occurred.", e.getErrorCode());

        return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorMessage());
    }
}
