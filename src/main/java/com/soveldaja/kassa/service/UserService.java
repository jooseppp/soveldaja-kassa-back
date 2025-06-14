package com.soveldaja.kassa.service;

import com.soveldaja.kassa.dto.UserDTO;
import com.soveldaja.kassa.entity.User;
import com.soveldaja.kassa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }


    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }


    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setUsername(userDTO.getUsername());
        // Only encode the password if it's been changed (not null or empty)
       /* if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }*/

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId().toString(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
