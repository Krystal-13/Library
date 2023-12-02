package com.rmtest.library.book.dto;

import com.rmtest.library.book.entity.BookStatus;
import com.rmtest.library.book.entity.Book;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookResponse {

    private Integer id;
    private String bookName;
    private String authorName;
    private String publisher;
    private String isbn;
    private BookStatus status;

    @Builder
    public BookResponse(Integer id, String bookName, String authorName, String publisher, String isbn, BookStatus status) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisher = publisher;
        this.isbn = isbn;
        this.status = status;
    }

    public static BookResponse ofEntity(Book book) {

        return BookResponse.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .authorName(book.getAuthorName())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .status(book.getStatus())
                .build();
    }
}
