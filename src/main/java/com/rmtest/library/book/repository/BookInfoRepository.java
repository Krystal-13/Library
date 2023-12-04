package com.rmtest.library.book.repository;

import com.rmtest.library.book.entity.Book;
import com.rmtest.library.book.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Integer> {

    Optional<BookInfo> findByBook(Book book);
}
