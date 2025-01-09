package com.williamkalogeropoulos.service;


import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.entity.Role;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.mapper.UserMapper;
import com.williamkalogeropoulos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserMapper::toDTO).orElse(null);
    }

    @Override
    public User saveUser(User user) {
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
}
