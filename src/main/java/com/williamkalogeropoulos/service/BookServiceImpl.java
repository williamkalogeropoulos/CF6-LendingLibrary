package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.mapper.BookMapper;
import com.williamkalogeropoulos.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .map(BookMapper::toDTO) // Converts Entity to DTO
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDTO)
                .orElse(null);
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
        return bookRepository.findAll(); // ✅ Now properly fetches all books
    }
}
