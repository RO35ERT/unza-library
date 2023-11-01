package com.example.unza_library.service;

import com.example.unza_library.dto.AddBookDto;
import com.example.unza_library.dto.Mapper;
import com.example.unza_library.dto.ToBookDto;
import com.example.unza_library.entity.Author;
import com.example.unza_library.entity.Book;
import com.example.unza_library.entity.Image;
import com.example.unza_library.entity.Publisher;
import com.example.unza_library.repository.AuthorRepository;
import com.example.unza_library.repository.BookRepository;
import com.example.unza_library.repository.ImageRepository;
import com.example.unza_library.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final PublisherRepository publisherRepository;
    private final ImageRepository imageRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           PublisherRepository publisherRepository, ImageRepository imageRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.imageRepository = imageRepository;
    }


    @Override
    public Book createBook(AddBookDto addBookDto) {
        Book book = new Book();
        book.setBookName(addBookDto.getBookName());
        book.setAuthorList(createAuthor(addBookDto));
        book.setDescription(addBookDto.getDescription());
        book.setImage(createImage(addBookDto));
        book.setIsbnNumber(addBookDto.getIsbn());
        book.setPublisher(createPublisher(addBookDto));
        book.setYearPublished(addBookDto.getYearPublished());
        book.setAccessionNumber(addBookDto.getAccession());
        book.setQuantity(addBookDto.getQuantity());
        book.setCopyNumber(addBookDto.getCopyNumber());
        book.setEdition(addBookDto.getEdition());
        return bookRepository.save(book);
    }

    @Override
    public List<Author> createAuthor(AddBookDto addBookDto) {
        List<Author> authors = new ArrayList<>();
        String authorName = addBookDto.getAuthor();
        String[] authorNames = authorName.split(",\\s*");

        for (String name: authorNames){
            Author retrievedName = authorRepository.findByAuthorNameIgnoreCase(name);
            if(retrievedName !=null){
                authors.add(retrievedName);
            }else {
                Author author = new Author();
                author.setAuthorName(name);
                Author savedAuthor = authorRepository.save(author);
                authors.add(savedAuthor);
            }
        }
        return authors;
    }

    @Override
    public Image createImage(AddBookDto addBookDto) {
        Image image = new Image();
        try {
            image.setImage(addBookDto.getCoverImage().getBytes());
        }catch (Exception e){
            System.out.println(e);
        }
        return imageRepository.save(image);
    }

    @Override
    public Publisher createPublisher(AddBookDto addBookDto) {
        Publisher publisher = new Publisher();

        Publisher publisher1 = publisherRepository.findByPublisherNameIgnoreCase(addBookDto.getPublisher());

        if(publisher1 != null){
            return publisher1;
        }
        publisher.setPublisherName(addBookDto.getPublisher());
        return publisherRepository.save(publisher);
    }

    @Override
    public List<ToBookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return Mapper.toBookDtos(books);
    }

    @Override
    public void deleteBook(String accession) {
        Book book = bookRepository.findById(accession).get();
        Image image = book.getImage();

        book.setAuthorList(null);
        book.setPublisher(null);
        bookRepository.deleteById(book.getAccessionNumber());
        imageRepository.deleteById(image.getImageId());
    }

    @Override
    public ToBookDto getBook(String accession) {
        Book book = bookRepository.findById(accession).get();
        return Mapper.toBookDto(book);
    }

    @Override
    public Page<Book> findPaginated(int pageNo,int pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        if(search != null){
            return bookRepository.findAllByBookNameContainingIgnoreCase(search,pageable);
        }
        return bookRepository.findAll(pageable);
    }


}
