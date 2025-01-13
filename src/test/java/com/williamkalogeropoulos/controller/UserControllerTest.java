package com.williamkalogeropoulos.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;
    private UserDTO userDTO1;
    private UserDTO userDTO2;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setEmail("testuser@example.com");

        userDTO1 = new UserDTO(1L, "testuser", "testuser@example.com", null);
        userDTO2 = new UserDTO(2L, "anotheruser", "anotheruser@example.com", null);
    }

    @Test
    void testGetUserByUsername() {
        when(userService.getUserByUsername("testuser")).thenReturn(userDTO1);

        ResponseEntity<UserDTO> response = userController.getUserByUsername("testuser");
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().getUsername());
        verify(userService, times(1)).getUserByUsername("testuser");
    }

    @Test
    void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(userDTO1, userDTO2));

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testRegisterUser() {
        when(userService.getUserByUsername("testuser")).thenReturn(null);
        doNothing().when(userService).registerUser(testUser);

        ResponseEntity<?> response = userController.registerUser(testUser);
        assertNotNull(response.getBody());
        assertEquals("User registered successfully.", response.getBody());
        verify(userService, times(1)).registerUser(testUser);
    }

    @Test
    void testUpdateUserRole() {
        when(userService.updateUserRole(1L, "ADMIN")).thenReturn(true);

        ResponseEntity<String> response = userController.updateUserRole(1L, "ADMIN");
        assertEquals("User role updated successfully", response.getBody());
        verify(userService, times(1)).updateUserRole(1L, "ADMIN");
    }

    @Test
    void testUpdateUser() {
        when(userService.updateUser(1L, userDTO1)).thenReturn(true);

        ResponseEntity<String> response = userController.updateUser(1L, userDTO1);
        assertEquals("User updated successfully", response.getBody());
        verify(userService, times(1)).updateUser(1L, userDTO1);
    }

    @Test
    void testDeleteUser() {
        when(userService.deleteUser(1L)).thenReturn(true);

        ResponseEntity<?> response = userController.deleteUser(1L);
        assertEquals("User deleted successfully.", response.getBody());
        verify(userService, times(1)).deleteUser(1L);
    }
}