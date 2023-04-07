package com.example.monara_backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String name, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
