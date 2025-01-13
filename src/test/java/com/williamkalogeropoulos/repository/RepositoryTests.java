package com.williamkalogeropoulos.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.williamkalogeropoulos.entity.Book;
import com.williamkalogeropoulos.entity.Borrowing;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepositoryTests {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowingRepository borrowingRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveAndFindBookById() {
        Book book = new Book(1L, "Test Book", "Test Author", "123456789", true);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book savedBook = bookRepository.save(book);
        Optional<Book> foundBook = bookRepository.findById(savedBook.getId());

        assertTrue(foundBook.isPresent());
        assertEquals("Test Book", foundBook.get().getTitle());
    }

    @Test
    void testFindByAvailableTrue() {
        Book book1 = new Book(1L, "Book One", "Author One", "111111111", true);
        Book book2 = new Book(2L, "Book Two", "Author Two", "222222222", false);
        when(bookRepository.findByAvailableTrue()).thenReturn(List.of(book1));

        List<Book> availableBooks = bookRepository.findByAvailableTrue();

        assertEquals(1, availableBooks.size());
        assertEquals("Book One", availableBooks.get(0).getTitle());
    }

    @Test
    void testFindByIsbn() {
        Book book = new Book(1L, "Unique Book", "Unique Author", "987654321", true);
        when(bookRepository.findByIsbn("987654321")).thenReturn(List.of(book));

        List<Book> booksByIsbn = bookRepository.findByIsbn("987654321");

        assertEquals(1, booksByIsbn.size());
        assertEquals("Unique Book", booksByIsbn.get(0).getTitle());
    }

    @Test
    void testSaveAndFindUserByUsername() {
        User user = new User("testuser", passwordEncoder.encode("password123"), "testuser@example.com", Role.USER);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findByUsername("testuser");

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testSaveAndFindBorrowingById() {
        User user = new User("testuser", passwordEncoder.encode("password123"), "testuser@example.com", Role.USER);
        Book book = new Book(1L, "Borrowed Book", "Author", "333333333", true);
        Borrowing borrowing = new Borrowing(user, book, LocalDate.now(), null, 0.0,true);
        borrowing.setId(1L);
        when(borrowingRepository.save(any(Borrowing.class))).thenReturn(borrowing);
        when(borrowingRepository.findById(1L)).thenReturn(Optional.of(borrowing));

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        Optional<Borrowing> foundBorrowing = borrowingRepository.findById(savedBorrowing.getId());

        assertTrue(foundBorrowing.isPresent());
    }
}
