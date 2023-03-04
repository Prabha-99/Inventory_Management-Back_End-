package com.example.monara_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = "User.findByEmailId",query = "SELECT user FROM User user WHERE user.user_email=:user_email") //user_name change that place

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "users")
public class  User implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "user_first_name")
    private String user_first_name;
    @Column(name = "user_last_name")
    private String user_last_name;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "user_email")
    private String user_email;
    @Column(name = "user_password")
    private String user_password;
    @Column(name= "status")
    private String status;
    @Column(name = "role")
    private String role;



}
