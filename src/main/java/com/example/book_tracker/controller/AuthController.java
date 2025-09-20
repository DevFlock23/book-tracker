package com.example.book_tracker.controller;

import com.example.book_tracker.security.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        logger.info("Login requested for username: {}", username);
        // In a real app, validate username/password!
        return JwtUtil.generateToken(username);
    }
}