package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {

    private final UserService userService;

    @Autowired
    public ResetPasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        User user = userService.getUserByResetToken(token);

        if (user == null) {
            model.addAttribute("error", "Invalid or expired token.");
            return "reset-password";
        }

        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping
    public String resetPassword(@RequestParam("token") String token,
                                @RequestParam("newPassword") String newPassword,
                                Model model) {
        User user = userService.getUserByResetToken(token);

        if (user == null) {
            model.addAttribute("error", "Invalid or expired token.");
            return "reset-password"; // Reload page with error
        }

        // Update password
        boolean updated = userService.updatePassword(user.getEmail(), newPassword);

        if (updated) {
            userService.clearResetToken(token); // Clear the token after reset
            model.addAttribute("success", "Password reset successfully!");
            return "login"; // Redirect to login after reset
        } else {
            model.addAttribute("error", "Failed to reset password. Try again.");
            return "reset-password";
        }
    }
}
