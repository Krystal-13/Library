package com.rmtest.library.book.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class BookRequest {
    private String bookName;
    private String authorName;
    private String publisher;
    private String isbn;
    private int count;

}
