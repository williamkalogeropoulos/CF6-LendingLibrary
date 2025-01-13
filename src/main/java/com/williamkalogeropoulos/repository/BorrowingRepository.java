package com.williamkalogeropoulos.repository;

import com.williamkalogeropoulos.entity.Borrowing;
import com.williamkalogeropoulos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    // ✅ Finds all borrowings by a user
    List<Borrowing> findByUser(User user);

    // ✅ Finds all currently active borrowings (not returned)
    List<Borrowing> findByReturnDateIsNull();

    // ✅ Checks if a book is currently borrowed
    boolean existsByBookIdAndReturnDateIsNull(Long bookId);

    // ✅ Deletes all borrowings related to a book before deleting the book
    void deleteByBookId(Long bookId);

    boolean existsByBookIdAndActiveTrue(Long bookId);
}
