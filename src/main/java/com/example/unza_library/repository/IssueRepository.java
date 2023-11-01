package com.example.unza_library.repository;

import com.example.unza_library.entity.Book;
import com.example.unza_library.entity.Issue;
import com.example.unza_library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {

    List<Issue> findAllByReturned(Boolean returned);

    Issue findIssueByUserAndReturned(User user, Boolean returned);
    Issue findByBook(Book book);
}
