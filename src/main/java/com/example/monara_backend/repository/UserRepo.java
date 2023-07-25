package com.example.monara_backend.repository;

import com.example.monara_backend.model.Report;
import com.example.monara_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAll();

    @Query(value = "SELECT email FROM users", nativeQuery = true)
    List<Report> getMails();


}
