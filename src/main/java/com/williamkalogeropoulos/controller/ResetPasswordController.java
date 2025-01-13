package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {

    @GetMapping
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token); // Pass token to the template
        return "reset-password"; // Ensure this matches the filename
    }

    @PostMapping
    public String resetPassword(@RequestParam("token") String token,
                                @RequestParam("newPassword") String newPassword,
                                Model model) {
        // Your reset logic here
        model.addAttribute("success", "Password reset successfully!");
        return "login"; // Redirect to login after reset
    }
}
