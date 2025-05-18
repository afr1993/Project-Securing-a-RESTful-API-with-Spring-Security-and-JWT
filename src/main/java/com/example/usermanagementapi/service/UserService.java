package com.example.usermanagementapi.service;

import com.example.usermanagementapi.entity.User;
import com.example.usermanagementapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!currentUser.getId().equals(id) && !currentUser.getRole().getName().equals("ADMIN")) {
            throw new AccessDeniedException("Access denied");
        }

        return userRepository.findById(id);
    }

    public String deleteUser(Long id, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!currentUser.getRole().getName().equals("ADMIN")) {
            throw new AccessDeniedException("Only admins can delete users");
        }

        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public User updateUser(Long id, User updatedUser, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!currentUser.getId().equals(id) && !currentUser.getRole().getName().equals("ADMIN")) {
            throw new AccessDeniedException("Access denied");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(updatedUser.getUsername());
        // No actualices la contraseña directamente aquí si está codificada
        return userRepository.save(user);
    }
}
