package com.example.unza_library.controller;

import com.example.unza_library.dto.AddBookDto;
import com.example.unza_library.service.BookService;
import com.example.unza_library.service.IssueService;
import com.example.unza_library.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private IssueService issueService;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("pending",reservationService.findAllReservations());
        return "admin";
    }

    @GetMapping("/add-book")
    public String showAddBook(Model model){
        AddBookDto addBookDto = new AddBookDto();
        model.addAttribute("addBook",addBookDto);
        System.out.println("Hi");
        return "add_book";
    }
    @PostMapping("/add-book")
    public String processAddBook(@Valid @ModelAttribute("addBook") AddBookDto addBookDto, BindingResult result, Model model){
        String message = null;
        if(result.hasErrors()){
            model.addAttribute("addBook",addBookDto);
            return "/add_book";
        }
        bookService.createBook(addBookDto);
        message = "The book was created successfull!";
        model.addAttribute("message",message);
        return "redirect:/admin/add-book?message=success";
    }

    @GetMapping("/issues")
    public String getIssues(Model model){
        model.addAttribute("issues",issueService.findAllPending());
        return "issues";
    }

    @GetMapping("/books")
    public String showBooks(Model model){
        model.addAttribute("books",bookService.getAllBooks());
        return "book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id){
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/approveReservation/{accession}")
    public String approveReservation(@PathVariable String accession){
        reservationService.approveReservation(accession);
        return "redirect:/admin/";
    }

    @GetMapping("/issue/{accession}")
    public String issue(@PathVariable String accession){
        issueService.approveIssue(accession);
      return "redirect:/admin/issues";
    }

    @GetMapping("/issued")
    public String issued(Model model){
        model.addAttribute("issues",issueService.findAllIssued());
        return "issued";
    }

}
