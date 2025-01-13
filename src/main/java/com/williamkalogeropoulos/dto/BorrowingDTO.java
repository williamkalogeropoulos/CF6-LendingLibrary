package com.williamkalogeropoulos.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.math.BigDecimal;

public class BorrowingDTO {
    // ✅ **Getters and Setters**
    @Getter
    private Long id;
    @Getter
    private String username;
    @Getter
    private Long bookId;
    @Getter
    private String bookTitle;
    @Getter
    private String bookAuthor;
    @Getter
    private String bookIsbn;
    @Getter
    private LocalDate borrowDate;
    @Getter
    private LocalDate returnDate;
    @Getter
    private BigDecimal overdueCharges;
    @Getter
    private Boolean active;

    // ✅ **Default No-Args Constructor (Needed for JSON serialization)**
    public BorrowingDTO() {}

    // ✅ **Fully Populated Constructor**
    public BorrowingDTO(Long id, String username, Long bookId, String bookTitle, String bookAuthor, String bookIsbn,
                        LocalDate borrowDate, LocalDate returnDate, BigDecimal overdueCharges,Boolean active) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookIsbn = bookIsbn;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.overdueCharges = overdueCharges;
        this.active = active;
    }

    public void setId(Long id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setBookId(Long bookId) { this.bookId = bookId; }

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }

    public void setBookIsbn(String bookIsbn) { this.bookIsbn = bookIsbn; }

    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public void setOverdueCharges(BigDecimal overdueCharges) { this.overdueCharges = overdueCharges; }
    public void setActive(Boolean active) { this.active = active; }
}
