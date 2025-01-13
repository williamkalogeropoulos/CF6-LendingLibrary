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
        return "forgot-password"; // Simple page to enter email
    }

    @PostMapping
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("error", "Email not found.");
            return "forgot-password";
        }

        model.addAttribute("email", email);
        return "reset-password"; // Redirect user to set a new password
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestParam("email") String email,
                                @RequestParam("newPassword") String newPassword,
                                Model model) {
        boolean success = userService.updatePassword(email, newPassword);

        if (success) {
            model.addAttribute("success", "Password reset successfully! You can now log in.");
            return "login";
        } else {
            model.addAttribute("error", "Error resetting password.");
            return "reset-password";
        }
    }
}
