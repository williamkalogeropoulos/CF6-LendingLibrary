package com.williamkalogeropoulos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.williamkalogeropoulos.entity.Role;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole(Role.USER);
    }

    @Test
    void testRegisterUser_Success() {
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = authService.registerUser(user);
        assertNotNull(result);
        assertEquals("hashedPassword", result.getPassword());
        assertEquals(Role.USER, result.getRole());

        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmptyPassword() {
        User invalidUser = new User();
        invalidUser.setUsername("testuser");
        // ❌ Do NOT set the password manually to "" since it will throw an exception in the entity

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authService.registerUser(invalidUser);
        });

        assertEquals("Password cannot be null or empty", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}
