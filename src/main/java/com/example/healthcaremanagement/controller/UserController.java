package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.User;
import com.example.healthcaremanagement.security.CurrentUser;
import com.example.healthcaremanagement.service.UserImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.saveUser(userService.createUser(user));
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        return "admin";
    }
}
