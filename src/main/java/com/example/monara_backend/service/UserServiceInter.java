package com.example.monara_backend.service;

import com.example.monara_backend.model.User;
import com.example.monara_backend.model.UserRole;

import java.util.Set;

public interface UserServiceInter {
    public User createUser(User user, Set<UserRole> userRole) throws Exception;

}
