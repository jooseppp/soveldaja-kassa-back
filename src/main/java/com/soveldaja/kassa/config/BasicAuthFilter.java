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
        filterChain.doFilter(request, response);

        /*// Skip authentication for login endpoint
        if (request.getRequestURI().equals("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Extract credentials
            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] values = credentials.split(":", 2);

            if (values.length == 2) {
                String username = values[0];
                String password = values[1];

                try {
                    // Validate credentials
                    UserDTO user = userService.getUserByUsername(username);

                    if (user.getPassword().equals(password)) {
                        // Set user info in request attributes for controllers to use
                        request.setAttribute("userId", user.getId());
                        request.setAttribute("username", user.getUsername());
                        request.setAttribute("userRole", user.getRole());

                        filterChain.doFilter(request, response);
                        return;
                    }
                } catch (Exception e) {
                    // User wasn't found or other error
                }
            }
        }

        // Authentication failed
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"Kassa Application\"");
        response.getWriter().write("{\"error\": \"Unauthorized\"}");

         */
    }
}