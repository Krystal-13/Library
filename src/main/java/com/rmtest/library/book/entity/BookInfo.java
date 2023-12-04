package com.rmtest.library.book.entity;

import com.rmtest.library.book.dto.ModifyBookRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Book book;

    @Column(columnDefinition = "varchar(100)")
    private String bookName;

    @Column(columnDefinition = "varchar(50)")
    private String authorName;

    @Column(columnDefinition = "varchar(50)")
    private String publisher;

    @Builder
    public BookInfo(Book book, String bookName, String authorName, String publisher) {
        this.book = book;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisher = publisher;
    }

    public void updateBookInfo(ModifyBookRequest book) {

        this.id = book.getBookInfoId();
        this.bookName = book.getBookName();
        this.authorName = book.getAuthorName();
        this.publisher = book.getPublisher();
    }
}
