package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookControllerThymeleaf {
    private final BookService bookService;

    public BookControllerThymeleaf(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.getPaginatedBooks(pageable);
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        return "books";
    }

    @GetMapping("/manage")
    @PreAuthorize("hasRole('ADMIN')")
    public String manageBooks(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.getPaginatedBooks(pageable);
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        return "manage-books";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setAvailable(true);
        bookService.saveBook(book);
        return "redirect:/books/manage";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookDTO book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateBook(@PathVariable Long id, @RequestParam String title, @RequestParam String author, @RequestParam String isbn) {
        bookService.updateBook(id, title, author, isbn);
        return "redirect:/books/manage";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/manage";
    }
}
