package com.example.monara_backend.service;

import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<Map<String, Object>> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<Map<String, Object>> users = new ArrayList<>();
        return users;
    }

    public User updateUser(Integer id, User user) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
//        if (userRepo.existsByUsername(user.getUsername())
//                && !existingUser.getUsername().equals(user.getUsername())) {
//            return null;
//        }
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(existingUser);
    }

}
