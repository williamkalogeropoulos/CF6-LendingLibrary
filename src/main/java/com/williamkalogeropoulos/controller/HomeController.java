package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to the Lending Library!");
        return "index"; // ✅ No ".html" needed
    }

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "manage-users";
    }
}