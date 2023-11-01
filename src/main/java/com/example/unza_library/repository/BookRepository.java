package com.example.unza_library.repository;

import com.example.unza_library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,String> {
    Page<Book> findAllByBookNameContainingIgnoreCase(String bookName, Pageable pageable);
}
