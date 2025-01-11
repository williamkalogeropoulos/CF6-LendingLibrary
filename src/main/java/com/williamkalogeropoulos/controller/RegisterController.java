package com.williamkalogeropoulos.controller;

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

        // ✅ 2. Check if username already exists
        if (userService.getUserByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists. Choose another one.");
            return "register";
        }

        // ✅ 3. Register the user and trigger a JavaScript pop-up
        userService.registerUser(user);
        model.addAttribute("success", "Registration successful! Redirecting to login...");
        return "register";  // ✅ Stay on the same page to allow JavaScript execution
    }
}
