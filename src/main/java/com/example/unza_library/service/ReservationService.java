package com.example.unza_library.service;

import com.example.unza_library.entity.Book;
import com.example.unza_library.entity.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    void createReservation(String accession, String compNumber);

    List<Reservation> findAllReservations();

    void approveReservation(String accession);
}
