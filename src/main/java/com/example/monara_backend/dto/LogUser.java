package com.example.monara_backend.dto;

import com.example.monara_backend.model.Role;
import com.example.monara_backend.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class LogUser {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

    public LogUser(User user) {
    }
}
