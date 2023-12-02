package com.rmtest.library.book.controller;

import com.rmtest.library.book.dto.BookRequest;
import com.rmtest.library.book.dto.BookResponse;
import com.rmtest.library.book.service.BookService;
import com.rmtest.library.book.dto.ModifyBookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<BookResponse> registerBook(@RequestBody BookRequest request, Principal principal) {

        return ResponseEntity.ok(bookService.registerBook(request, principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookInfo(@PathVariable Integer id, Principal principal) {

        return ResponseEntity.ok(bookService.getBookInfo(id, principal.getName()));
    }

    @PatchMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<BookResponse> modifyBook(@RequestBody ModifyBookRequest request, Principal principal) {

        return ResponseEntity.ok(bookService.modifyBook(request, principal.getName()));
    }


}
