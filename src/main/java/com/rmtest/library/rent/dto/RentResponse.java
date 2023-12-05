package com.rmtest.library.rent.dto;

import com.querydsl.core.Tuple;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.rmtest.library.book.entity.QBook.book;
import static com.rmtest.library.rent.QRent.rent;
import static com.rmtest.library.user.entity.QUser.user;

@Getter
public class RentResponse {

    private Integer bookId;
    private String userName;
    private LocalDate startDay;
    private LocalDate endDay;
    private String status;

    @Builder
    public RentResponse(Integer bookId, String userName,
                        LocalDate startDay, LocalDate endDay, String status) {
        this.bookId = bookId;
        this.userName = userName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.status = status;
    }

    public static List<RentResponse> ofTuple(List<Tuple> tuples) {

        if (tuples.isEmpty()) {
            return Collections.emptyList();
        }

        List<RentResponse> list = new ArrayList<>();
        for (Tuple x : tuples) {

            RentResponse rentResponse =
                    RentResponse.builder()
                                .bookId(x.get(book.id))
                                .startDay(x.get(rent.startDay))
                                .endDay(x.get(rent.endDay))
                                .userName(x.get(user.userName))
                                .status(Objects.requireNonNull(x.get(rent.status)).getMassage())
                                .build();
            list.add(rentResponse);
        }

        return list;
    }
}
