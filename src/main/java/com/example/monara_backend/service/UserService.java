package com.example.monara_backend.service;

import com.example.monara_backend.dto.UserDto;
import com.example.monara_backend.model.Role;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.RoleRepo;
import com.example.monara_backend.repository.UserRepo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName((userDto.getFirstname() + " " + userDto.getLastname()));
        user.setUser_name(userDto.getUsername());
        user.setUser_email(userDto.getEmail());
        user.setUser_password(userDto.getPassword());

        Role role = roleRepo.findByName("Admin"); // Check Role
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles((Arrays.asList(role)));
        userRepo.save(user);
    }
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }
    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstname(str[0]);
        userDto.setLastname(str[1]);
        userDto.setEmail(user.getUser_email());
        return userDto;
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("Stock_Manager");
        return roleRepo.save(role);
    }
}
