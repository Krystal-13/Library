package com.rmtest.library.rent.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.rmtest.library.book.entity.QBook.book;
import static com.rmtest.library.rent.QRent.rent;
import static com.rmtest.library.user.entity.QUser.user;

@Repository
public class CustomRentRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CustomRentRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Tuple> getBookRentalList(Integer bookId, LocalDate from, LocalDate to) {

        return jpaQueryFactory.select(book.id, rent.startDay, rent.endDay, rent.status, user.userName)
                .from(rent)
                .leftJoin(book).on(book.eq(rent.book))
                .leftJoin(user).on(user.eq(rent.user))
                .where(book.id.in(bookId).and(rent.startDay.between(from,to)))
                .orderBy(rent.startDay.desc())
                .fetch();
    }
}
