package com.rmtest.library.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStatus {
    POSSIBLE("대출가능"),
    IMPOSSIBLE("대출중");

    private final String status;
}
