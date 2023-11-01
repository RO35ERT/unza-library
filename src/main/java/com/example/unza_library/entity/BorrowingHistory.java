package com.example.unza_library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrowing_history")
public class BorrowingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bId;

    @OneToOne
    @JoinColumn(name = "user_comp_number")
    private User user;

    @Max(4)
    private byte borrowed;
}
