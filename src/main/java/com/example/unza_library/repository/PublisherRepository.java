package com.example.unza_library.repository;

import com.example.unza_library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {
    Publisher findByPublisherNameIgnoreCase(String publisherName);
}
