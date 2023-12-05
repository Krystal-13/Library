package com.rmtest.library.book.dto;

import com.rmtest.library.book.entity.BookInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookResponse {

    private Integer bookInfoId;
    private String bookName;
    private String authorName;
    private String publisher;
    private String isbn;
    private int count;

    @Builder
    public BookResponse(Integer bookInfoId, String bookName, String authorName,
                        String publisher, String isbn, int count) {
        this.bookInfoId = bookInfoId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisher = publisher;
        this.isbn = isbn;
        this.count = count;
    }

    public static BookResponse ofEntity(BookInfo bookInfo) {

        return BookResponse.builder()
                .bookInfoId(bookInfo.getId())
                .bookName(bookInfo.getBookName())
                .authorName(bookInfo.getAuthorName())
                .publisher(bookInfo.getPublisher())
                .isbn(bookInfo.getBook().getIsbn())
                .count(bookInfo.getBook().getCount())
                .build();
    }


}
