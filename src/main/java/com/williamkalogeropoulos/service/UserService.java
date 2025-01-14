package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.entity.User;

import java.util.List;

public interface UserService {
    UserDTO getUserByUsername(String username);
    User saveUser(User user);

    // ✅ New methods for reset token handling
    String generateResetToken(String email);
    User getUserByResetToken(String token);
    void clearResetToken(String token);

    List<UserDTO> getAllUsers();
    boolean updateUserRole(Long id, String role);
    boolean deleteUser(Long id);
    boolean updateUser(Long id, UserDTO userDTO); // ✅ Allows updating user details

    void registerUser(User user); // ✅ New method for user registration

    User getUserByEmail(String email);
    boolean updatePassword(String email, String newPassword);
}