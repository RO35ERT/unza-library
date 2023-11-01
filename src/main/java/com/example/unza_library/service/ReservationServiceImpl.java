package com.example.unza_library.service;

import com.example.unza_library.config.EmailSender;
import com.example.unza_library.entity.*;
import com.example.unza_library.repository.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;

    private final ReservationRepository reservationRepository;

    private final BorrowingHistoryRepository borrowingHistoryRepository;

    private final EmailSender emailSender;


    @Autowired
    public ReservationServiceImpl(BookRepository bookRepository, UserRepository userRepository, ReservationRepository reservationRepository, BorrowingHistoryRepository borrowingHistoryRepository, EmailSender emailSender, IssueRepository issueRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.borrowingHistoryRepository = borrowingHistoryRepository;
        this.emailSender = emailSender;
        this.issueRepository = issueRepository;
    }

    @Override
    public void createReservation(String accession, String compNumber) {
        Reservation reservation = new Reservation();
        Book book = bookRepository.findById(accession).get();
        User user = userRepository.findByCompNumber(compNumber);

        reservation.setBook(book);
        reservation.setStatus(false);
        reservation.setUser(user);

        @Nullable
        BorrowingHistory borrowingHistory = borrowingHistoryRepository.findByUser(user);
        byte borrowingTimes;

        if(borrowingHistory == null){
                BorrowingHistory borrowingHistory1 = new BorrowingHistory();
                reservationRepository.save(reservation);
                borrowingHistory1.setBorrowed((byte) 1);
                borrowingHistory1.setUser(user);
                borrowingHistoryRepository.save(borrowingHistory1);
                emailSender.sendReservation(user,book);
        } else {
            borrowingTimes = borrowingHistory.getBorrowed();
            if(borrowingTimes <4){
                Book book1 = bookRepository.findById(accession).get();
                Reservation reservation1 = reservationRepository.findByBookAndStatus(book1,false);
                Issue issue = issueRepository.findIssueByUserAndReturned(user,true);
                if(reservation1 == null && issue == null){
                    reservationRepository.save(reservation);
                    borrowingHistory.setBorrowed((byte) (borrowingTimes+1));
                    borrowingHistoryRepository.save(borrowingHistory);
                    emailSender.sendReservation(user,book);
                }else{
                    emailSender.sendRejectedReservation(user,book);
                }
            }else{
                emailSender.sendRejectionReservation(user,book);
            }
        }
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAllByStatus(false);
    }

    @Override
    public void approveReservation(String accession) {
        Book book = bookRepository.findById(accession).get();
        Reservation reservation = reservationRepository.findByBookAndStatus(book,false);
        reservation.setStatus(true);
        Reservation reservation1 =reservationRepository.save(reservation);
        User user = reservation1.getUser();
        Issue issue = new Issue();
        issue.setBook(book);
        issue.setPenalty(0.0);
        issue.setUser(user);
        issueRepository.save(issue);
        emailSender.sendApprovedReservation(user,book);
    }
}
