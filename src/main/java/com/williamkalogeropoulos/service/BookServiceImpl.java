package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.mapper.BookMapper;
import com.williamkalogeropoulos.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAvailableBooks() {
        return bookRepository.findByAvailableTrue()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<BookDTO> getBooksByIsbn(String ISBN) {
        return bookRepository.findByIsbn(ISBN)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateBook(Long id, String title, String author, String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(title);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setAvailable(true);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }

    @Override
    @Transactional
    public void resetAllBooks() {
        bookRepository.updateAllBooksToAvailable();
    }

    @Override
    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCase(query);
    }

    @Override
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    @Override
    public List<Book> searchBooksByTitleAndAuthor(String query, String author) {
        return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(query, author);
    }

    @Override
    public Book findByIsbn(String isbn) {
        return (Book) bookRepository.findByIsbn(isbn);
    }
}
