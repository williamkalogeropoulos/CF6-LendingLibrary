package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookControllerThymeleaf {
    private final BookService bookService;

    public BookControllerThymeleaf(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/add")
    public String showAddBookForm() {
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String title, @RequestParam String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
