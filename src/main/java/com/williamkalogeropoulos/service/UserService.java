package com.williamkalogeropoulos.service;

import com.williamkalogeropoulos.dto.UserDTO;
import com.williamkalogeropoulos.entity.User;

public interface UserService {
    UserDTO getUserByUsername(String username);
    User saveUser(User user);
}