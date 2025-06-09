package com.soveldaja.kassa.config;

import com.soveldaja.kassa.dto.UserDTO;
import com.soveldaja.kassa.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDTO userDTO = userService.getUserByUsername(username);
            
            return new User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userDTO.getRole().toUpperCase()))
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}