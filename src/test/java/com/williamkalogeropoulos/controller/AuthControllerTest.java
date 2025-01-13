package com.williamkalogeropoulos.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
    }

    @Test
    void testRegisterUser() {
        when(authService.registerUser(any(User.class))).thenReturn(testUser);

        ResponseEntity<User> response = authController.registerUser(testUser);
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().getUsername());
        verify(authService, times(1)).registerUser(any(User.class));
    }
}
