package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.repository.BorrowingRepository;
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
    private final BorrowingRepository borrowingRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.borrowingRepository = borrowingRepository;
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
    @Transactional
    public void deleteBook(Long id) {
        if (isBookCurrentlyBorrowed(id)) {
            throw new RuntimeException("Cannot delete book: It is currently borrowed.");
        }

        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll(); // ✅ Now properly fetches all books
    }

    @Override
    public void updateBook(Long id, String title, String author, String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(title);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setAvailable(true); // ✅ Ensure books are marked available when updated
            bookRepository.save(book);  // ✅ Save the updated book
        } else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }

    @Override
    @Transactional // ✅ Ensures the query runs in a transaction
    public void resetAllBooks() {
        bookRepository.updateAllBooksToAvailable();
    }

    @Override
    public boolean isBookCurrentlyBorrowed(Long bookId) {
        return borrowingRepository.existsByBookIdAndReturnDateIsNull(bookId);
    }
}
