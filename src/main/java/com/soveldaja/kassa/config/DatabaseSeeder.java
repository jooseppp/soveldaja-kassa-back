package com.soveldaja.kassa.config;

import com.soveldaja.kassa.entity.User;
import com.soveldaja.kassa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        // Check and create an admin user if not exists
        createUserIfNotExists("admin", "admin", "ADMIN");

        // Check and create a default user if not exists
        createUserIfNotExists("user", "user", "USER");
    }


    private void createUserIfNotExists(String username, String password, String role) {
        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole(role);

            userRepository.save(newUser);
            System.out.println("Created " + role + " user: " + username);
        } else {
            System.out.println(role + " user already exists: " + username);
        }
    }
}
