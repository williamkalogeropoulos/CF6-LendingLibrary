package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.dto.BorrowingDTO;
import com.williamkalogeropoulos.service.BorrowingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@Tag(name = "Borrowing Management", description = "APIs for borrowing and returning books")
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @Operation(summary = "Get active borrowings", description = "Fetches a list of books that are currently borrowed and not yet returned")
    @GetMapping("/active")
    public ResponseEntity<List<BorrowingDTO>> getActiveBorrowings() {
        return ResponseEntity.ok(borrowingService.getAllActiveBorrowings());
    }

    @Operation(summary = "Get user borrowings", description = "Fetches all borrowings associated with the logged-in user")
    @GetMapping("/my-borrowings")
    public ResponseEntity<List<BorrowingDTO>> getUserBorrowings(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(borrowingService.getUserBorrowings(userDetails.getUsername()));
    }

    @Operation(summary = "Borrow a book", description = "Allows the logged-in user to borrow a book")
    @PostMapping("/{bookId}")
    public ResponseEntity<BorrowingDTO> borrowBook(@AuthenticationPrincipal UserDetails userDetails,
                                                   @PathVariable Long bookId) {
        return ResponseEntity.ok(borrowingService.borrowBook(userDetails.getUsername(), bookId));
    }

    @Operation(summary = "Return a book (User)", description = "Allows the logged-in user to return their own book")
    @PostMapping("/{id}/return")
    public ResponseEntity<String> returnBook(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        borrowingService.returnBook(userDetails.getUsername(), id);
        return ResponseEntity.ok("Book returned successfully");
    }

    @Operation(summary = "Admin return any book", description = "Allows an admin to return any user's borrowed book")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/admin-return")
    public ResponseEntity<String> adminReturnBook(@PathVariable Long id) {
        borrowingService.adminReturnBook(id);
        return ResponseEntity.ok("Book returned successfully by admin");
    }
}
