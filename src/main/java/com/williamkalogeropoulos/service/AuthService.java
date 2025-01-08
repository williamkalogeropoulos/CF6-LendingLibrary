package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.entity.Role;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ Ensure password is hashed
        user.setRole(Role.USER); // ✅ Default role is USER
        return userRepository.save(user);
    }
}
