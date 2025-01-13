package com.williamkalogeropoulos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "borrowings")
public class Borrowing {
    // ✅ Getters and Setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "overdue_charges", nullable = false)
    private BigDecimal overdueCharges;

    @Column(name = "active", nullable = false)
    private Boolean active;

    public Borrowing() {}

    public Borrowing(User user, Book book, LocalDate borrowDate, LocalDate returnDate, Double overdueCharges, Boolean active) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.overdueCharges = BigDecimal.valueOf(overdueCharges);
        this.active = active;
    }

}
