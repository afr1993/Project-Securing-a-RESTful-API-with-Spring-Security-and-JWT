package com.example.usermanagementapi.controller;

import com.example.usermanagementapi.entity.User;
import com.example.usermanagementapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id, Authentication authentication) {
        return userService.getUserById(id, authentication);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id, Authentication authentication) {
        return userService.deleteUser(id, authentication);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User updatedUser,
                           Authentication authentication) {
        return userService.updateUser(id, updatedUser, authentication);
    }
}
