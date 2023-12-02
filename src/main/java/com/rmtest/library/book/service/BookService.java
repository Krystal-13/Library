package com.rmtest.library.book.service;

import com.rmtest.library.book.entity.BookStatus;
import com.rmtest.library.book.dto.ModifyBookRequest;
import com.rmtest.library.book.dto.BookRequest;
import com.rmtest.library.book.dto.BookResponse;
import com.rmtest.library.book.entity.Book;
import com.rmtest.library.book.repository.BookRepository;
import com.rmtest.library.exception.CustomException;
import com.rmtest.library.exception.ErrorCode;
import com.rmtest.library.user.entity.User;
import com.rmtest.library.user.entity.UserRole;
import com.rmtest.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BookService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public BookResponse registerBook(BookRequest request, String email) {

        if (!this.getUser(email).getRole().equals(UserRole.MANAGER)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        if (bookRepository.findByIsbn(request.getIsbn()).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_BOOK);
        }

        Book book = Book.builder()
                        .bookName(request.getBookName())
                        .authorName(request.getAuthorName())
                        .publisher(request.getPublisher())
                        .isbn(request.getIsbn())
                        .status(BookStatus.POSSIBLE)
                        .build();

        return BookResponse.ofEntity(bookRepository.save(book));
    }

    public BookResponse getBookInfo(Integer id, String email) {

        this.getUser(email);

        Book book = bookRepository.findById(id).orElseThrow(() ->
                                        new CustomException(ErrorCode.BOOK_NOT_FOUND));

        return BookResponse.ofEntity(book);
    }

    @Transactional
    public BookResponse modifyBook(ModifyBookRequest request, String email) {

        if (!this.getUser(email).getRole().equals(UserRole.MANAGER)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        Book book = bookRepository.findById(request.getId()).orElseThrow(() ->
                                        new CustomException(ErrorCode.BOOK_NOT_FOUND));

        book.updateBook(request);

        return BookResponse.ofEntity(book);
    }

    private User getUser(String email) {

        return userRepository.findByEmail(email).orElseThrow(() ->
                                        new CustomException(ErrorCode.USER_NOT_FOUND));
    }

}
