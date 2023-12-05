package com.rmtest.library.rent.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RentStatus {
    RENTING("대여중"),
    RETURNED("반납완료");

    private final String massage;
}
