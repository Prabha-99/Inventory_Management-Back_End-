package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NamedQuery(name = "User.findByEmailId",query = "SELECT user FROM User user WHERE user.user_email=:user_email") //user_name change that place

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class  User  {


    @Id
    private int user_id;

    private String user_first_name;

    private String user_last_name;

    private String user_name;

    private String user_email;

    private String user_password;

    private String role;



}
