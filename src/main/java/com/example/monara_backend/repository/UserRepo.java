package com.example.monara_backend.repository;

import com.example.monara_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Integer> {

    //public List<UserDto> findByUser_name(String user_name); // can change that username
}
