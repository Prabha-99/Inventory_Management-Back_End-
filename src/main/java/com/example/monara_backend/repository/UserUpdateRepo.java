package com.example.monara_backend.repository;

import com.example.monara_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUpdateRepo extends JpaRepository<User,Integer> {

}
