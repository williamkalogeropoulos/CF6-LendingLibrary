package com.williamkalogeropoulos.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.williamkalogeropoulos.dto.BookDTO;
import com.williamkalogeropoulos.dto.BorrowingDTO;
import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.entity.Borrowing;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class MapperTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testBookToDTO() {
        Book book = new Book(1L, "Book Title", "Author Name", "123456789", true);

        BookDTO bookDTO = BookMapper.toDTO(book);

        assertNotNull(bookDTO);
        assertEquals(1L, bookDTO.getId());
        assertEquals("Book Title", bookDTO.getTitle());
        assertEquals("Author Name", bookDTO.getAuthor());
        assertEquals("123456789", bookDTO.getIsbn());
        assertTrue(bookDTO.getAvailable());
    }

    @Test
    void testUserToDTO() {
        User user = new User("testuser", "password123", "test@example.com", Role.USER);
        user.setId(1L);

        UserDTO userDTO = UserMapper.toDTO(user);

        assertNotNull(userDTO);
        assertEquals(1L, userDTO.getId());
        assertEquals("testuser", userDTO.getUsername());
        assertEquals("test@example.com", userDTO.getEmail());
        assertEquals(Role.USER, userDTO.getRole());
    }

    @Test
    void testBorrowingToDTO() {
        User user = new User("testuser", "password123", "test@example.com", Role.USER);
        user.setId(1L);
        Book book = new Book(1L, "Book Title", "Author Name", "123456789", true);
        Borrowing borrowing = new Borrowing(user, book, LocalDate.now(), null, 0.0);
        borrowing.setId(1L);

        BorrowingDTO borrowingDTO = BorrowingMapper.toDTO(borrowing);

        assertNotNull(borrowingDTO);
        assertEquals(1L, borrowingDTO.getId());
        assertEquals(1L, borrowingDTO.getBookId());
        assertEquals("Book Title", borrowingDTO.getBookTitle());
        assertEquals("Author Name", borrowingDTO.getBookAuthor());
        assertEquals("123456789", borrowingDTO.getBookIsbn());
        assertEquals(LocalDate.now(), borrowingDTO.getBorrowDate());
        assertNull(borrowingDTO.getReturnDate());
    }
}
