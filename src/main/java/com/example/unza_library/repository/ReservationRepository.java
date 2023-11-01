package com.example.unza_library.repository;

import com.example.unza_library.entity.Book;
import com.example.unza_library.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findAllByStatus(Boolean status);
    Reservation findByBookAndStatus(Book book, Boolean status);
}
