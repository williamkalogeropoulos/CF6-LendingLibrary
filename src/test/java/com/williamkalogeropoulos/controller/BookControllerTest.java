package com.williamkalogeropoulos.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookController bookController;

    private BookDTO book1;
    private BookDTO book2;

    @BeforeEach
    void setUp() {
        book1 = new BookDTO(1L, "Book One", "Author One", "123456789", true);
        book2 = new BookDTO(2L, "Book Two", "Author Two", "987654321", true);
    }

    @Test
    void testGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(
                new Book(1L, "Book One", "Author One", "123456789", true),
                new Book(2L, "Book Two", "Author Two", "987654321", true)
        ));

        ResponseEntity<List<Book>> response = bookController.getAllBooks();
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testGetBookById() {
        when(bookService.getBookById(1L)).thenReturn(
                new BookDTO(1L, "Book One", "Author One", "123456789", true)
        );

        ResponseEntity<BookDTO> response = bookController.getBookById(1L);
        assertNotNull(response.getBody());
        assertEquals("Book One", response.getBody().getTitle());
        verify(bookService, times(1)).getBookById(1L);
    }
}