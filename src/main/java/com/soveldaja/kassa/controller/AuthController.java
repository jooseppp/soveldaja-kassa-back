package com.soveldaja.kassa.controller;

import com.soveldaja.kassa.dto.UserDTO;
import com.soveldaja.kassa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        try {
            UserDTO user = userService.getUserByUsername(username);

            // Simple password check - in a real application, you would use proper password hashing
            if (!user.getPassword().equals(password)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
            }

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("username", user.getUsername());
            userMap.put("role", user.getRole());

            response.put("user", userMap);
            // In a real application, you would generate a proper JWT token
            response.put("token", "dummy-token-" + username);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // In a real application, you would invalidate the token
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}