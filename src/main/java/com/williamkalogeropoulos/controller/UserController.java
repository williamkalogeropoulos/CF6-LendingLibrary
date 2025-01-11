package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // ✅ This controller is for REST API calls, not rendering pages
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user by username", description = "Fetch user details by username")
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all users", description = "Fetch all users from the database")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Update user role", description = "Modify a user's role (admin/user)")
    @PutMapping("/{id}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable Long id, @RequestParam String role) {
        boolean updated = userService.updateUserRole(id, role);
        return updated ? ResponseEntity.ok("User role updated successfully")
                : ResponseEntity.badRequest().body("Invalid role or user not found");
    }

    @Operation(summary = "Update user information", description = "Modify user details such as username")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        boolean updated = userService.updateUser(id, userDTO);
        return updated ? ResponseEntity.ok("User updated successfully")
                : ResponseEntity.badRequest().body("User not found or invalid data");
    }

    @Operation(summary = "Delete a user", description = "Removes a user from the system")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = userService.deleteUser(id);
            return deleted ? ResponseEntity.ok("User deleted successfully.")
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error deleting user.");
        }
    }
}
