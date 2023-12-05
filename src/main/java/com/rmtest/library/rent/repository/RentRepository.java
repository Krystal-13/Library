package com.rmtest.library.rent.repository;

import com.rmtest.library.rent.entity.Rent;
import com.rmtest.library.rent.entity.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

    Optional<Rent> findByUser_IdAndBook_IdAndStatus(Integer userId, Integer bookId, RentStatus status);
}
