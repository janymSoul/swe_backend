package com.example.swe_project.services;

import com.example.swe_project.entity.User;
import com.example.swe_project.entity.Role;
import com.example.swe_project.entity.UserStatus;
import com.example.swe_project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create User
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE.name());
        user.setRole(Role.BUYER.name());
        user.setEnabled(true);
        return userRepository.save(user);
    }

    // Read User by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Read all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update User
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if(userDetails.getUsername() != null)
            user.setUsername(userDetails.getUsername());
        if(userDetails.getEmail() != null)
            user.setEmail(userDetails.getEmail());
        if(userDetails.getPassword() != null)
            user.setPassword(userDetails.getPassword());
        if(userDetails.getRole() != null)
            user.setRole(userDetails.getRole());
        if(userDetails.getStatus() != null)
            user.setRole(userDetails.getStatus());
        return userRepository.save(user);
    }

    // Delete User
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

}
