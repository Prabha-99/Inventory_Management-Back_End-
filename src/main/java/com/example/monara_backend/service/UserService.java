package com.example.monara_backend.service;

import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;

    public List<User> getAllUsers() {
            return userRepo.findAll();
        }

    public User getUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

//    public User updateUser(Integer id, User user) {
//        User existingUser = userRepo.findById(id).orElse(null);
//        if (existingUser == null) {
//            return null;
//        }
//        existingUser.setFirstname(user.getFirstname());
//        existingUser.setLastname(user.getLastname());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepo.save(existingUser);
//    }
    public User updateUser(User user) {
       Integer id = user.getId();

       User user1 = userRepo.findById(id).get();
       user1.setId(user.getId());
       user1.setFirstname(user.getFirstname());
       user1.setLastname(user.getLastname());
       user1.setEmail(user.getEmail());
       user1.setRole(user.getRole());
       return userRepo.save(user1);
    }


    public HttpStatus deleteUser(Integer id) {
        try {
            User user = userRepo.findById(id).orElse(null);
            if (user == null) {
                return HttpStatus.NOT_FOUND;
            }


            userRepo.delete(user);
            return HttpStatus.NO_CONTENT;

        } catch (EmptyResultDataAccessException e) {
            return HttpStatus.NOT_FOUND;
        } catch (DataIntegrityViolationException e) {
            return HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    //Users profile update
    public User updateProfile(Integer id, User profile) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setFirstname(profile.getFirstname());
        existingUser.setLastname(profile.getLastname());
        existingUser.setEmail(profile.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(profile.getPassword());
        existingUser.setPassword(encodedPassword);
      //existingUser.setRole(profile.getRole());
        return userRepo.save(existingUser);
    }
}
