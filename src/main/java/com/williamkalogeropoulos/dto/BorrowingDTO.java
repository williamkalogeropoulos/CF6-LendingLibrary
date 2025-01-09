package com.williamkalogeropoulos.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public class BorrowingDTO {
    private Long id;
    private String username;
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BigDecimal overdueCharges;

    // ✅ **Default No-Args Constructor (Needed for JSON serialization)**
    public BorrowingDTO() {}

    // ✅ **Fully Populated Constructor**
    public BorrowingDTO(Long id, String username, Long bookId, String bookTitle, String bookAuthor, String bookIsbn,
                        LocalDate borrowDate, LocalDate returnDate, BigDecimal overdueCharges) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookIsbn = bookIsbn;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.overdueCharges = overdueCharges;
    }

    // ✅ **Getters and Setters**
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getBookAuthor() { return bookAuthor; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }

    public String getBookIsbn() { return bookIsbn; }
    public void setBookIsbn(String bookIsbn) { this.bookIsbn = bookIsbn; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public BigDecimal getOverdueCharges() { return overdueCharges; }
    public void setOverdueCharges(BigDecimal overdueCharges) { this.overdueCharges = overdueCharges; }
}
