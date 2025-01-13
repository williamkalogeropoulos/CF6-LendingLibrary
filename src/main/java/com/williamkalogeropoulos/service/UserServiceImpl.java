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
    public void registerUser(User user) {
        // ✅ Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists.");
        }

        // ✅ Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ✅ Assign default role USER
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole()))
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

            // ✅ Update username if provided
            if (userDTO.getUsername() != null && !userDTO.getUsername().trim().isEmpty()) {
                user.setUsername(userDTO.getUsername());
            }

            // ✅ Update email if provided
            if (userDTO.getEmail() != null && !userDTO.getEmail().trim().isEmpty()) {
                user.setEmail(userDTO.getEmail());
            }

// ✅ Update role safely (no need for .valueOf() since it's already an Enum)
            if (userDTO.getRole() != null) {
                try {
                    user.setRole(userDTO.getRole()); // ✅ Directly set Enum
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid role: " + userDTO.getRole());
                }
            }

            userRepository.save(user);
            return true;
        }
        return false;
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        User user = getUserByEmail(email);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
