package com.example.unza_library.service;

import com.example.unza_library.dto.AddBookDto;
import com.example.unza_library.dto.ToBookDto;
import com.example.unza_library.entity.Author;
import com.example.unza_library.entity.Book;
import com.example.unza_library.entity.Image;
import com.example.unza_library.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book createBook(AddBookDto addBookDto);

    List<Author> createAuthor(AddBookDto addBookDto);
    Image createImage(AddBookDto addBookDto);
    Publisher createPublisher(AddBookDto addBookDto);

    List<ToBookDto> getAllBooks();

    void deleteBook(String accession);

    ToBookDto getBook(String accession);

    Page<Book> findPaginated(int pageSize,int pageNo,String search);
}
