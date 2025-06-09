package com.soveldaja.kassa.config;

import com.soveldaja.kassa.entity.User;
import com.soveldaja.kassa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check and create admin user if not exists
        createUserIfNotExists("admin", "admin", "ADMIN");

        // Check and create default user if not exists
        createUserIfNotExists("user", "user", "USER");
    }

    private void createUserIfNotExists(String username, String password, String role) {
        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setRole(role);

            userRepository.save(newUser);
            System.out.println("Created " + role + " user: " + username);
        } else {
            System.out.println(role + " user already exists: " + username);
        }
    }
}
