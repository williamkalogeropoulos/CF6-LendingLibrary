package com.williamkalogeropoulos.dto;

import com.williamkalogeropoulos.entity.Role;

public class UserDTO {
    private Long id;
    private String username;
    private Role role; // Enum: USER, ADMIN

    // ✅ Default constructor (Required for JSON deserialization)
    public UserDTO() {}

    // ✅ Correct Constructor
    public UserDTO(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // ❌ Removed invalid constructor that used javax.management.relation.Role

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}