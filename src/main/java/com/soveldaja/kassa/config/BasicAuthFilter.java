package com.soveldaja.kassa.config;

import com.soveldaja.kassa.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This filter is no longer used for authentication.
 * Authentication is now handled by Spring Security.
 * See SecurityConfig and CustomUserDetailsService for the new implementation.
 */
@Component
public class BasicAuthFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Autowired
    public BasicAuthFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Just pass the request through to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
