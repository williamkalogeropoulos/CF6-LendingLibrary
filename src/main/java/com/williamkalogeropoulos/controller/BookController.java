package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Management", description = "APIs for managing books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books", description = "Fetches a list of all books (available & unavailable)")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/all")
    public Page<Book> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.getPaginatedBooks(pageable);
        return bookPage;
    }

    @Operation(summary = "Get all available books", description = "Fetches a list of available books for borrowing")
    @GetMapping("/available")
    public ResponseEntity<List<BookDTO>> getAvailableBooks() {
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }

    @Operation(summary = "Get book by ID", description = "Fetches details of a specific book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBookById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get book by ISBN", description = "Fetches details of a specific book by ISBN")
    @GetMapping("/{ISBN}")
    public ResponseEntity<List<BookDTO>> getBookByIsbn(@PathVariable String ISBN) {
        List<BookDTO> books = bookService.getBooksByIsbn(ISBN);
        return books != null ? ResponseEntity.ok(books) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new book", description = "Allows admins to add new books to the system")
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @Operation(summary = "Delete a book", description = "Allows admins to delete a book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/reset-availability")
    @PreAuthorize("hasRole('ADMIN')") // ✅ Ensures only admins can access
    public ResponseEntity<String> resetBookAvailability() {
        try {
            bookService.resetAllBooks();
            return ResponseEntity.ok("All books have been reset to available.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam String query, @RequestParam String type) {
        switch (type) {
            case "author":
                List<BookDTO> authors =  bookService.searchBooksByAuthor(query);
                return authors;
            case "title":
                List<BookDTO> titles =  bookService.searchBooksByTitle(query);
                return titles;
            case "isbn":
                List<BookDTO> isbns =  bookService.searchBooksByIsbn(query);
                return isbns;
            default:
                return List.of();
        }
    }
}

