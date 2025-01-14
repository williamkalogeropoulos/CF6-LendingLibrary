package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    private final UserService userService;

    @Autowired
    public ForgotPasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showForgotPasswordForm() {
        return "forgot-password"; // Page where user enters email
    }

    @PostMapping
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("error", "Email not found.");
            return "forgot-password";
        }

        // Generate a reset token
        String token = userService.generateResetToken(email);
        String resetLink = "http://localhost:8080/reset-password?token=" + token;

        // ✅ Show the reset link on the page instead of sending an email
        model.addAttribute("resetLink", resetLink);

        return "forgot-password"; // Stay on the page and show the link
    }
}
