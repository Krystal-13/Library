package com.rmtest.library.rent.entity;

import com.rmtest.library.book.entity.Book;
import com.rmtest.library.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;
    private LocalDate startDay;
    private LocalDate endDay;

    @Enumerated(EnumType.STRING)
    private RentStatus status;

    @Builder
    public Rent(Book book, User user, LocalDate startDay, LocalDate endDay, RentStatus status) {
        this.book = book;
        this.user = user;
        this.startDay = startDay;
        this.endDay = endDay;
        this.status = status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }
}

