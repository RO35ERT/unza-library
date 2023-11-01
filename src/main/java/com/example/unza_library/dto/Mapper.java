package com.example.unza_library.dto;

import com.example.unza_library.entity.Author;
import com.example.unza_library.entity.Book;
import com.example.unza_library.entity.Image;
import com.example.unza_library.entity.Publisher;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Mapper {

    public static ToBookDto toBookDto(Book book){
        ToBookDto book1 = new ToBookDto();
        book1.setQuantity(book.getQuantity());
        book1.setBookName(book.getBookName());
        book1.setIsbn(book.getIsbnNumber());
        book1.setAccession(book.getAccessionNumber());
        Publisher publisher = book.getPublisher();
        book1.setDescription(book.getDescription());
        book1.setPublisher(publisher.getPublisherName());
        book1.setYearPublished(book.getYearPublished());
        List<Author> authors = book.getAuthorList();

        StringBuilder authorList = new StringBuilder();
        for (int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            authorList.append(author.getAuthorName());
            // Check if it's not the last author before appending the ampersand
            if (i < authors.size() - 1) {
                authorList.append(" & ");
            }
        }
        book1.setAuthor(authorList.toString());
        book1.setCopyNumber(book.getCopyNumber());
        book1.setEdition(book.getEdition());
        Image image = book.getImage();
        book1.setCoverImage(Base64.getEncoder().encodeToString(image.getImage()));

        return book1;
    }

    public static List<ToBookDto> toBookDtos(List<Book> books){
        List<ToBookDto> bookDtos = new ArrayList<>();

        for(Book book : books){
            bookDtos.add(toBookDto(book));
        }
        return bookDtos;
    }
}
