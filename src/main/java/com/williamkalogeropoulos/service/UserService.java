package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.entity.User;

import java.util.List;

public interface UserService {
    UserDTO getUserByUsername(String username);
    User saveUser(User user);
    List<UserDTO> getAllUsers();
    boolean updateUserRole(Long id, String role);
}
