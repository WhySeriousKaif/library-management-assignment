package com.assignment.controller;

import com.assignment.model.Book;
import com.assignment.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        model.addAttribute("books", libraryService.getAllBooksWithAuthors());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute Book book, Model model) {
        try {
            libraryService.saveBook(book);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating book: " + e.getMessage());
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "create";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        try {
            Book book = libraryService.getBookById(id);
            model.addAttribute("book", book);
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "update";
        } catch (IllegalArgumentException e) {
            return "redirect:/";
        }
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book, Model model) {
        try {
            libraryService.saveBook(book);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating book: " + e.getMessage());
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "update";
        }
    }
}
