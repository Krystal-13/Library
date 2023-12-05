package com.rmtest.library.book.service;

import com.rmtest.library.book.dto.BookRequest;
import com.rmtest.library.book.dto.BookResponse;
import com.rmtest.library.book.dto.ModifyBookRequest;
import com.rmtest.library.book.entity.Book;
import com.rmtest.library.book.entity.BookInfo;
import com.rmtest.library.book.repository.BookInfoRepository;
import com.rmtest.library.book.repository.BookRepository;
import com.rmtest.library.exception.CustomException;
import com.rmtest.library.exception.ErrorCode;
import com.rmtest.library.user.entity.User;
import com.rmtest.library.user.entity.UserRole;
import com.rmtest.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final UserRepository userRepository;
    private final BookInfoRepository bookInfoRepository;
    private final BookRepository bookRepository;

    public BookService(UserRepository userRepository,
                       BookInfoRepository bookInfoRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookInfoRepository = bookInfoRepository;
        this.bookRepository = bookRepository;
    }

    public BookResponse registerBook(BookRequest request, String email) {

        if (!this.getUser(email).getRole().equals(UserRole.MANAGER)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        Optional<Book> optionalBook = bookRepository.findByIsbn(request.getIsbn());

        if (optionalBook.isPresent()) {
            BookInfo bookInfo = this.plusBookCount(optionalBook.get(), request.getCount());

            return BookResponse.ofEntity(bookInfoRepository.save(bookInfo));
        }

        Book savedBook = this.saveBook(request);

        BookInfo bookInfo = BookInfo.builder()
                                    .book(savedBook)
                                    .bookName(request.getBookName())
                                    .authorName(request.getAuthorName())
                                    .publisher(request.getPublisher())
                                    .build();

        return BookResponse.ofEntity(bookInfoRepository.save(bookInfo));
    }

    private BookInfo plusBookCount(Book book, int count) {

        book.plusCount(count);

        return bookInfoRepository.findByBook(book).orElseThrow(() ->
                new CustomException(ErrorCode.BOOK_INFO_NOT_FOUND));
    }

    public BookResponse getBookInfo(Integer id, String email) {

        this.getUser(email);

        BookInfo bookInfo = bookInfoRepository.findById(id).orElseThrow(() ->
                                        new CustomException(ErrorCode.BOOK_INFO_NOT_FOUND));

        return BookResponse.ofEntity(bookInfo);
    }

    @Transactional
    public BookResponse modifyBook(ModifyBookRequest request, String email) {

        if (!this.getUser(email).getRole().equals(UserRole.MANAGER)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        BookInfo bookInfo = bookInfoRepository.findById(request.getBookInfoId()).orElseThrow(() ->
                                        new CustomException(ErrorCode.BOOK_INFO_NOT_FOUND));

        bookInfo.updateBookInfo(request);

        Book book = bookInfo.getBook();
        book.setCount(request.getCount());

        return BookResponse.ofEntity(bookInfo);
    }

    private User getUser(String email) {

        return userRepository.findByEmail(email).orElseThrow(() ->
                                        new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private Book saveBook(BookRequest request) {

        Book book = Book.builder()
                .isbn(request.getIsbn())
                .count(request.getCount())
                .build();

        return bookRepository.save(book);
    }

}
