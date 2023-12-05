package com.rmtest.library.rent.controller;

import com.rmtest.library.rent.dto.RentRequest;
import com.rmtest.library.rent.dto.RentResponse;
import com.rmtest.library.rent.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/books/{id}/{from}/{to}")
    public ResponseEntity<List<RentResponse>> getRentList(@PathVariable Integer id,
                                                          @PathVariable LocalDate from,
                                                          @PathVariable LocalDate to,
                                                          Principal principal) {

        return ResponseEntity.ok(rentService.getRentList(id, from, to, principal.getName()));
    }

    @PostMapping()
    public ResponseEntity<Boolean> rentBook(@RequestBody RentRequest request, Principal principal) {

        return ResponseEntity.ok(rentService.rentBook(request, principal.getName()));
    }

    @PutMapping()
    public ResponseEntity<Boolean> returnBook(@RequestBody RentRequest request, Principal principal) {

        return ResponseEntity.ok(rentService.returnBook(request, principal.getName()));
    }

}
