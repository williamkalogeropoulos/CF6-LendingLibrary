package com.williamkalogeropoulos.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public class BorrowingDTO {
    private Long id;
    private String username;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BigDecimal overdueCharges; // ✅ Ensure this matches BigDecimal

    // ✅ Correct Constructor
    public BorrowingDTO(Long id, String username, Long bookId, LocalDate borrowDate, LocalDate returnDate, BigDecimal overdueCharges) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.overdueCharges = overdueCharges;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public BigDecimal getOverdueCharges() { return overdueCharges; }
    public void setOverdueCharges(BigDecimal overdueCharges) { this.overdueCharges = overdueCharges; }
}
