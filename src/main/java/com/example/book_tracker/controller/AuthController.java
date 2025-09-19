package com.example.book_tracker.controller;

import com.example.book_tracker.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public String login(@RequestParam String username) {
        // In a real app, validate username/password!
        return JwtUtil.generateToken(username);
    }
}