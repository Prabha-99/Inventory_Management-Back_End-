package com.example.monara_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "Please Fill All The Field")
    private String firstname;
    @NotEmpty(message = "Please Fill All The Field")
    private String lastname;

    @NotEmpty(message = "Please Fill All The Field")
    private String username;
    @NotEmpty(message = "Please Fill All The Field")
    @Email
    private String email;
    @NotEmpty(message = "Please Fill All The Field")
    private String password;

}
