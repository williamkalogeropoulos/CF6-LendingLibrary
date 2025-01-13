package com.williamkalogeropoulos.repository;


import com.williamkalogeropoulos.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.available = true")
    List<Book> findByAvailableTrue(); // Find only available books
    List<Book> findByIsbn(String ISBN); // find books with ISBN
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByIsbnContainingIgnoreCase(String isbn);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.available = true")
    void updateAllBooksToAvailable();
}
