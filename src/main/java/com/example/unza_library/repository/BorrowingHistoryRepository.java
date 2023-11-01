package com.example.unza_library.repository;

import com.example.unza_library.entity.BorrowingHistory;
import com.example.unza_library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingHistoryRepository extends JpaRepository<BorrowingHistory,Long> {
    BorrowingHistory findByUser(User user);
}
