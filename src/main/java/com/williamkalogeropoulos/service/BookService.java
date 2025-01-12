package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<BookDTO> getAvailableBooks();
    BookDTO getBookById(Long id);
    List<BookDTO> getBooksByIsbn(String ISBN);
    Book saveBook(Book book);

    // ✅ Updated deleteBook method to prevent foreign key constraint issues
    void deleteBook(Long id);

    // ✅ Added method to fetch all books for the UI
    List<Book> getAllBooks();

    void updateBook(Long id, String title, String author, String isbn);

    void resetAllBooks();

    // ✅ New method to check if a book is currently borrowed before deleting
    boolean isBookCurrentlyBorrowed(Long bookId);

    Page<Book> getPaginatedBooks(Pageable pageable);
}