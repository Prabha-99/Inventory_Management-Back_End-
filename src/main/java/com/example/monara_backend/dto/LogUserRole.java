package com.example.monara_backend.dto;

import com.example.monara_backend.model.Role;


public class LogUserRole {
    private Role role;

    public LogUserRole(String role) {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
