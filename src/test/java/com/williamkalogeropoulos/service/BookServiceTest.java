package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.exception.BookNotFoundException;
import com.williamkalogeropoulos.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetBookByIdThrowsException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1L));

        assertEquals("Book with ID 1 not found", exception.getMessage());
    }
}