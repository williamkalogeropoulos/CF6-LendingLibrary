package com.williamkalogeropoulos.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.williamkalogeropoulos.dto.BorrowingDTO;
import com.williamkalogeropoulos.service.BorrowingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BorrowingControllerTest {

    @Mock
    private BorrowingService borrowingService;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private BorrowingController borrowingController;

    private BorrowingDTO borrowing1;
    private BorrowingDTO borrowing2;

    @BeforeEach
    void setUp() {
        borrowing1 = new BorrowingDTO(1L, "testuser", 1L, "Book Title 1", "Author 1", "123456789", LocalDate.of(2024, 1, 1), null, BigDecimal.ZERO,true);
        borrowing2 = new BorrowingDTO(2L, "testuser2", 2L, "Book Title 2", "Author 2", "987654321", LocalDate.of(2024, 1, 2), null, BigDecimal.ZERO,true);
    }

    @Test
    void testGetActiveBorrowings() {
        when(borrowingService.getAllActiveBorrowings()).thenReturn(Arrays.asList(borrowing1, borrowing2));

        ResponseEntity<List<BorrowingDTO>> response = borrowingController.getActiveBorrowings();
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(borrowingService, times(1)).getAllActiveBorrowings();
    }

    @Test
    void testGetUserBorrowings() {
        when(userDetails.getUsername()).thenReturn("testuser");
        when(borrowingService.getUserBorrowings("testuser")).thenReturn(Arrays.asList(borrowing1, borrowing2));

        ResponseEntity<List<BorrowingDTO>> response = borrowingController.getUserBorrowings(userDetails);
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(borrowingService, times(1)).getUserBorrowings("testuser");
    }

    @Test
    void testBorrowBook() {
        when(userDetails.getUsername()).thenReturn("testuser");
        when(borrowingService.borrowBook("testuser", 1L)).thenReturn(borrowing1);

        ResponseEntity<BorrowingDTO> response = (ResponseEntity<BorrowingDTO>) borrowingController.borrowBook(userDetails, 1L);
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getBookId());
        verify(borrowingService, times(1)).borrowBook("testuser", 1L);
    }

    @Test
    void testReturnBook() {
        when(userDetails.getUsername()).thenReturn("testuser");

        ResponseEntity<String> response = (ResponseEntity<String>) borrowingController.returnBook(userDetails, 1L);
        assertNotNull(response.getBody());
        assertEquals("Book returned successfully", response.getBody());
        verify(borrowingService, times(1)).returnBook("testuser", 1L);
    }

    @Test
    void testAdminReturnBook() {
        ResponseEntity<String> response = (ResponseEntity<String>) borrowingController.adminReturnBook(1L);
        assertNotNull(response.getBody());
        assertEquals("Book returned successfully by admin", response.getBody());
        verify(borrowingService, times(1)).adminReturnBook(1L);
    }
}