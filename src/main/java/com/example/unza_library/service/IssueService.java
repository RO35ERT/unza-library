package com.example.unza_library.service;

import com.example.unza_library.entity.Issue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueService {
    List<Issue> findAllPending();
    Issue approveIssue(String accession);

    List<Issue> findAllIssued();
}
