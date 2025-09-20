package com.example.book_tracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(
            @org.springframework.lang.NonNull HttpServletRequest request,
            @org.springframework.lang.NonNull HttpServletResponse response,
            @org.springframework.lang.NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        logger.debug("Incoming request: {} {}", request.getMethod(), request.getRequestURI());
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                String username = JwtUtil.validateTokenAndGetUsername(token);
                logger.info("JWT validated for user: {}", username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, null);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                logger.warn("JWT validation failed: {}", e.getMessage());
            }
        } else {
            logger.debug("No JWT token found in Authorization header.");
        }
        filterChain.doFilter(request, response);
    }
}