package com.example.unza_library.dto;

import jakarta.validation.constraints.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookDto {

    @NotEmpty(message = "ISBN Number cannot be null")
    private String isbn;
    @NotEmpty(message = "Accession Number cannot be null")
    private String accession;

    @NotEmpty(message = "Book Name cannot be null")
    private String bookName;

    @NotEmpty(message = "Author cannot be null")
    private String author;

    @NotNull(message = "Quantity cannot be 0")
    @Min(1)
    private Long quantity;

    private MultipartFile coverImage;

    private int edition;

    @NotNull(message = "Copy cannot be 0")
    @Min(1)
    private int copyNumber;
    @NotEmpty(message = "Description cannot be null")
    private String description;
    @NotEmpty(message = "Publisher cannot be null")
    private String publisher;
    @NotEmpty(message = "Year published cannot be null")
    private String yearPublished;
}
