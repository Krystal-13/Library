package com.rmtest.library.rent.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RentRequest {
    private Integer userId;
    private Integer bookId;

    @Builder
    public RentRequest(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
