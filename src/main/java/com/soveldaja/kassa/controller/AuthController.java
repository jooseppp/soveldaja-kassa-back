package com.soveldaja.kassa.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soveldaja.kassa.dto.UserDTO;
import com.soveldaja.kassa.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Not authenticated"));
        }

        try {
            UserDTO user = userService.getUserByUsername(principal.getName());

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("username", user.getUsername());
            userMap.put("role", user.getRole());

            response.put("user", userMap);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }
    }
}
