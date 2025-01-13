package com.williamkalogeropoulos.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false) // ✅ Ensure email is unique
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // Ensures role is not null
    private Role role; // ENUM: USER or ADMIN

    // ✅ Default Constructor (Required by JPA)
    public User() {}

    // ✅ Constructor including email
    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;  // ✅ Now email is correctly assigned
        this.role = role;
    }

    // Getters
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; } // ✅ Ensure email getter is present
    public Role getRole() { return role; }

    @Override
    public String getPassword() { return password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_" + role.name());
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    // Proper Setter Methods
    public void setUsername(String username) { this.username = username; }

    public void setEmail(String email) { this.email = email; } // ✅ Setter for email

    public void setPassword(String password) {
        if (password != null && !password.isEmpty()) {
            this.password = password; // Ensure the password is hashed before setting
        } else {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

    public void setRole(Role role) { this.role = role; }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Borrowing> borrowings;
}
