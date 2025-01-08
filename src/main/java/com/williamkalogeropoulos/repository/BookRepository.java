package com.williamkalogeropoulos.repository;


import com.williamkalogeropoulos.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAvailableTrue(); // Find only available books
}
