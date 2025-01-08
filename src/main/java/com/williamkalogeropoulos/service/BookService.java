package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import java.util.List;

public interface BookService {
    List<BookDTO> getAvailableBooks();
    BookDTO getBookById(Long id);
    List<BookDTO> getBooksByIsbn(String ISBN);
    Book saveBook(Book book);
    void deleteBook(Long id);

    // ✅ Added method to fetch all books for the UI
    List<Book> getAllBooks();
}
