package com.example.unza_library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToBookDto {

    private String isbn;

    private String accession;


    private String bookName;


    private String author;


    private Long quantity;

    private String coverImage;

    private int edition;

    private int copyNumber;

    private String description;
    private String publisher;
    private String yearPublished;
}
