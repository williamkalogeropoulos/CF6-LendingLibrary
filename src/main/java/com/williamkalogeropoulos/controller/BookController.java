package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam("query") String query) {
        logger.info("Received search request with query: {}", query);
        List<Book> books = bookService.searchBooks(query);
        if (books.isEmpty()) {
            logger.info("No books found for query: {}", query);
            return ResponseEntity.noContent().build();
        }
        logger.info("Returning {} books for query: {}", books.size(), query);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<Book> bookDetails(@PathVariable("isbn") String isbn) {
        logger.info("Fetching details for book with ISBN: {}", isbn);
        Book book = bookService.findByIsbn(isbn);
        return Optional.ofNullable(book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Book not found with ISBN: {}", isbn);
                    return ResponseEntity.notFound().build();
                });
    }
}