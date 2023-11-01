package com.example.unza_library.service;

import com.example.unza_library.config.EmailSender;
import com.example.unza_library.entity.Book;
import com.example.unza_library.entity.Issue;
import com.example.unza_library.repository.BookRepository;
import com.example.unza_library.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    private final BookRepository bookRepository;

    private final EmailSender emailSender;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, BookRepository bookRepository, EmailSender emailSender) {
        this.issueRepository = issueRepository;
        this.bookRepository = bookRepository;
        this.emailSender = emailSender;
    }

    @Override
    public List<Issue> findAllPending() {
        return issueRepository.findAllByReturned(null);
    }

    @Override
    public Issue approveIssue(String accession) {
        Book book = bookRepository.findById(accession).get();
        Long quantity = book.getQuantity();
        book.setQuantity(quantity-1);
        Issue issue = issueRepository.findByBook(book);
        issue.setReturned(false);
        issue.setCollection(new Date());
        emailSender.sendCollection(issue.getUser(),issue.getBook());
        bookRepository.save(book);
        return issueRepository.save(issue);
    }

    @Override
    public List<Issue> findAllIssued() {
        return issueRepository.findAllByReturned(false);
    }
}
