package com.williamkalogeropoulos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.mapper.BookMapper;
import com.williamkalogeropoulos.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book1;
    private Book book2;
    private BookDTO bookDTO1;
    private BookDTO bookDTO2;

    @BeforeEach
    void setUp() {
        book1 = new Book(1L, "Book One", "Author One", "123456789", true);
        book2 = new Book(2L, "Book Two", "Author Two", "987654321", true);

        bookDTO1 = new BookDTO(1L, "Book One", "Author One", "123456789", true);
        bookDTO2 = new BookDTO(2L, "Book Two", "Author Two", "987654321", true);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();
        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));

        BookDTO result = bookService.getBookById(1L);
        assertNotNull(result);
        assertEquals("Book One", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        Book result = bookService.saveBook(book1);
        assertNotNull(result);
        assertEquals("Book One", result.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}
