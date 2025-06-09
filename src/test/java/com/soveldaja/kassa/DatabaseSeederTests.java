package com.soveldaja.kassa;

import com.soveldaja.kassa.entity.User;
import com.soveldaja.kassa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseSeederTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testDefaultUsersCreated() {
        // Check if admin user exists
        Optional<User> adminUser = userRepository.findByUsername("admin");
        assertTrue(adminUser.isPresent(), "Admin user should be created by DatabaseSeeder");
        assertEquals("ADMIN", adminUser.get().getRole(), "Admin user should have ADMIN role");

        // Check if default user exists
        Optional<User> defaultUser = userRepository.findByUsername("user");
        assertTrue(defaultUser.isPresent(), "Default user should be created by DatabaseSeeder");
        assertEquals("USER", defaultUser.get().getRole(), "Default user should have USER role");
    }
}