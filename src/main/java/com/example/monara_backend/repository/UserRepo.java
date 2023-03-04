package com.example.monara_backend.repository;

import com.example.monara_backend.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmailId(@Param("user_email") String user_email); // can change that username
}
