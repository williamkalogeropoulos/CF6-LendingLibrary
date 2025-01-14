package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.entity.Role;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("success", null); // ✅ Add a default value for success
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        // ✅ 1. Check if any field is blank
        if (user.getUsername().trim().isEmpty() ||
                user.getPassword().trim().isEmpty() ||
                user.getEmail().trim().isEmpty()) {
            model.addAttribute("error", "All fields are required.");
            return "register";
        }

        // ✅ Check if username already exists
        if (userService.getUserByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists. Choose another one.");
            return "register";
        }

        // ✅ Check if email already exists
        if (userService.getUserByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists. Try logging in.");
            return "register";
        }

        // ✅ Assign default role if not provided
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole(Role.valueOf("USER"));
        }

        // ✅ 4. Register the user
        userService.registerUser(user);
        model.addAttribute("success", "Registration successful! Redirecting to login...");
        return "register";  // ✅ Stay on the same page to allow JavaScript execution
    }
}
