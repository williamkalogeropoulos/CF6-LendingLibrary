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
    List<Book> getAllBooks();
    void updateBook(Long id, String title, String author, String isbn);
    void resetAllBooks();
    List<Book> searchBooks(String query);
    List<Book> searchBooksByAuthor(String author);
    List<Book> searchBooksByTitleAndAuthor(String query, String author);
    Book findByIsbn(String isbn);
}