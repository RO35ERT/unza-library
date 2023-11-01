package com.example.unza_library.repository;

import com.example.unza_library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findByAuthorNameIgnoreCase(String authorName);
}
