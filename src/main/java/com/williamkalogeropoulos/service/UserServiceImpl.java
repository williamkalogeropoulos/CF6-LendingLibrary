package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.entity.Role;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.mapper.UserMapper;
import com.williamkalogeropoulos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = (BCryptPasswordEncoder) passwordEncoder;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserMapper::toDTO).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Ensure role is valid
        if (user.getRole() == null || (!user.getRole().equals(Role.USER) && !user.getRole().equals(Role.ADMIN))) {
            user.setRole(Role.USER); // Default role to USER if none provided
        }

        return userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateUserRole(Long id, String role) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (role.equalsIgnoreCase("USER") || role.equalsIgnoreCase("ADMIN")) {
                user.setRole(Role.valueOf(role.toUpperCase()));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userDTO.getUsername() != null && !userDTO.getUsername().trim().isEmpty()) {
                user.setUsername(userDTO.getUsername());
            }

            // ✅ Fix: Convert Role from String to Enum before setting it
            if (userDTO.getRole() != null) {
                user.setRole(userDTO.getRole()); // ✅ Directly set Enum (No .trim())
            }

            userRepository.save(user);
            return true;
        }
        return false;
    }
}