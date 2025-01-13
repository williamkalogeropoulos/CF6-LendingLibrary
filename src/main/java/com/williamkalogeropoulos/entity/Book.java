package com.williamkalogeropoulos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "books")
public class Book {
    public Book(long l, String bookOne, String authorOne, String number, boolean b) {
}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = true)
    private String isbn;

    @Column(nullable = false)
    private Boolean available = true;

    public Book() {

    }

    // Getters and Setters
}
