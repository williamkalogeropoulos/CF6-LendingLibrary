package com.williamkalogeropoulos.controller;

import com.williamkalogeropoulos.dto.BorrowingDTO;
import com.williamkalogeropoulos.service.BorrowingService; // Import BorrowingService
import com.williamkalogeropoulos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final BorrowingService borrowingService; // Declare BorrowingService

    @Autowired
    public HomeController(UserService userService, BorrowingService borrowingService) {
        this.userService = userService;
        this.borrowingService = borrowingService; // Inject BorrowingService
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to the Lending Library!");
        return "index"; // ✅ No ".html" needed
    }

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "manage-users";
    }

    @GetMapping("/my-borrowings")
    public String myBorrowingsPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Fetch user borrowings
        List<BorrowingDTO> borrowings = borrowingService.getUserBorrowings(userDetails.getUsername());

        // Pass borrowings to Thymeleaf template
        model.addAttribute("borrowings", borrowings);

        return "my-borrowings"; // Return the Thymeleaf template
    }
    @GetMapping("/admin/borrowings")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewAdminBorrowings(Model model) {
        model.addAttribute("borrowings", borrowingService.getAllActiveBorrowings());
        return "borrowings"; // Maps to borrowings.html
    }
    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us"; // Name should match about-us.html (without extension)
    }
    @GetMapping("/contact-us")
    public String contactUs() {
        return "contact-us"; // Matches contact-us.html in templates folder
    }
}

