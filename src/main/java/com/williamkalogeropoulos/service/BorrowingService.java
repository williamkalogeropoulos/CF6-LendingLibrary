package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.BorrowingDTO;
import java.util.List;

public interface BorrowingService {
    List<BorrowingDTO> getAllActiveBorrowings();
    List<BorrowingDTO> getUserBorrowings(String username);
    BorrowingDTO borrowBook(String username, Long bookId);
    BorrowingDTO returnBook(String username, Long borrowingId);

    // ✅ New method for admins to return any book
    BorrowingDTO adminReturnBook(Long borrowingId);
}
