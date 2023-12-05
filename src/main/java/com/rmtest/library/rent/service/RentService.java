package com.rmtest.library.rent.service;

import com.querydsl.core.Tuple;
import com.rmtest.library.book.entity.Book;
import com.rmtest.library.book.repository.BookRepository;
import com.rmtest.library.exception.CustomException;
import com.rmtest.library.exception.ErrorCode;
import com.rmtest.library.rent.dto.RentRequest;
import com.rmtest.library.rent.dto.RentResponse;
import com.rmtest.library.rent.entity.Rent;
import com.rmtest.library.rent.entity.RentStatus;
import com.rmtest.library.rent.repository.CustomRentRepository;
import com.rmtest.library.rent.repository.RentRepository;
import com.rmtest.library.user.entity.User;
import com.rmtest.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final CustomRentRepository customRentRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public RentService(RentRepository rentRepository, CustomRentRepository customRentRepository,
                       BookRepository bookRepository, UserRepository userRepository) {
        this.rentRepository = rentRepository;
        this.customRentRepository = customRentRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<RentResponse> getRentList(Integer id, LocalDate from, LocalDate to, String email) {

        this.getUser(email);

        List<Tuple> bookRentalList = customRentRepository.getBookRentalList(id, from, to);

        return RentResponse.ofTuple(bookRentalList);
    }

    @Transactional
    public Boolean rentBook(RentRequest request, String email) {

        User user = this.getUser(email);

        if (!Objects.equals(request.getUserId(), user.getId())) {
            throw new CustomException(ErrorCode.UNMATCHED_USER);
        }

        Book book = this.getBook(request.getBookId());

        LocalDate startDay = LocalDate.now();
        LocalDate endDay = LocalDate.now().plusDays(7);

        Rent rent = Rent.builder()
                .book(book)
                .user(user)
                .startDay(startDay)
                .endDay(endDay)
                .status(RentStatus.RENTING)
                .build();
        rentRepository.save(rent);

        book.minusCount();

        return true;
    }

    @Transactional
    public Boolean returnBook(RentRequest request, String email) {

        User user = this.getUser(email);

        if (!Objects.equals(request.getUserId(), user.getId())) {
            throw new CustomException(ErrorCode.UNMATCHED_USER);
        }

        Book book = this.getBook(request.getBookId());

        Rent rent = rentRepository.findByUser_IdAndBook_IdAndStatus(
                    request.getUserId(), request.getBookId(), RentStatus.RENTING)
                            .orElseThrow(() -> new CustomException(ErrorCode.RENT_NOT_FOUND));

        if (rent.getEndDay().isAfter(LocalDate.now())) {
            throw new CustomException(ErrorCode.OVERDUE_BOOK);
        }

        rent.setStatus(RentStatus.RETURNED);
        book.plusCount(1);

        return true;
    }

    private User getUser(String email) {

        return userRepository.findByEmail(email).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private Book getBook(Integer bookId) {

        return bookRepository.findById(bookId).orElseThrow(() ->
                new CustomException(ErrorCode.BOOK_INFO_NOT_FOUND));
    }

}

