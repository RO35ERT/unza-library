package com.example.unza_library.controller;

import com.example.unza_library.dto.Mapper;
import com.example.unza_library.dto.ToBookDto;
import com.example.unza_library.entity.Book;
import com.example.unza_library.service.BookService;
import com.example.unza_library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    public BookService bookService;

    @Autowired
    public ReservationService reservationService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false,name = "search")String search){

        model.addAttribute("books",bookService.getAllBooks());
        findPaginated(1,model,search);
        return "index";
    }

    @GetMapping("/book/{id}")
    public String showBook(Model model, @PathVariable String id){
        model.addAttribute("book",bookService.getBook(id));
        return "book_details";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo, Model model,String search){
        int pageSize = 8;
        Page<Book> page = bookService.findPaginated(pageNo,pageSize,search);
        List<Book> books = page.getContent();
        List<ToBookDto> toBookDto = Mapper.toBookDtos(books);
        model.addAttribute("bPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("bBook", toBookDto);
        return "index";
    }

    @GetMapping("/book/reservation/{accession}")
    public String createReservation(@PathVariable String accession){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        System.out.println("Hello");
        reservationService.createReservation(accession,userDetails.getUsername());
        return "redirect:/book/"+accession;
    }
}
