package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
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
        return updated ? ResponseEntity.ok("User role updated successfully") : ResponseEntity.badRequest().body("Invalid role or user not found");
    }
}
