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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(100)")
    private String bookName;

    @Column(columnDefinition = "varchar(50)")
    private String authorName;

    @Column(columnDefinition = "varchar(50)")
    private String publisher;

    @Column(columnDefinition = "varchar(20)")
    private String isbn;

    @Column(columnDefinition = "varchar(10)")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Builder
    public Book(String bookName, String authorName, String publisher, String isbn, BookStatus status) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisher = publisher;
        this.isbn = isbn;
        this.status = status;
    }

    public void updateBook(ModifyBookRequest book) {

        this.id = book.getId();
        this.bookName = book.getBookName();
        this.authorName = book.getAuthorName();
        this.publisher = book.getPublisher();
        this.isbn = book.getIsbn();
    }
}
