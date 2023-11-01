package com.example.unza_library.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "books")
public class Book {

    @Id
    private String accessionNumber;

    private String bookName;
    private String isbnNumber;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    private String yearPublished;
    private String description;
    private int copyNumber;
    private int edition;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(referencedColumnName = "accessionNumber"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "authorId")

    )
    private List<Author> authorList;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
