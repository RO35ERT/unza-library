package com.example.unza_library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "accession")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_cmp_number")
    private User user;

    private Boolean status;

}
