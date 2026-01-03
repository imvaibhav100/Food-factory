package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.services.UserServices;

@Controller
public class UserController {

    @Autowired
    private UserServices services;

    // ================= REGISTER =================

    // Show registration page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());   // 🔥 NAME MATCH
        return "register";
    }

    // Handle registration
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") User user,
            Model model) {

        if (services.getUserByEmail(user.getUemail()) != null) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }

        services.addUser(user);
        return "redirect:/login";
    }

    // ================= ADMIN USER CRUD =================

    @PostMapping("/addingUser")
    public String addUser(@ModelAttribute User user) {
        services.addUser(user);
        return "redirect:/admin/services";
    }

    @GetMapping("/updatingUser/{id}")
    public String updateUser(@ModelAttribute User user, @PathVariable int id) {
        services.updateUser(user, id);
        return "redirect:/admin/services";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        services.deleteUser(id);
        return "redirect:/admin/services";
    }
}
