package com.example.monara_backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private int user_id;
    private String user_first_name;
    private String user_last_name;
    private String user_name;
    private String user_email;
    private String user_password;
    private String status;
    private String role;
}
