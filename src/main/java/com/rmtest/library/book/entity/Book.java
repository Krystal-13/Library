package com.rmtest.library.book.entity;

import com.rmtest.library.exception.CustomException;
import com.rmtest.library.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20)")
    private String isbn;

    private int count;

    @Builder
    public Book(String isbn, int count) {
        this.isbn = isbn;
        this.count = count;
    }

    public void plusCount(int count) {
        this.count += count;
    }

    public void minusCount() {
        this.count -= 1;

        if (this.count < 0) {
            throw new CustomException(ErrorCode.OUT_OF_STOCK);
        }
    }

    public void setCount(int count) {
        this.count = count;
    }
}
