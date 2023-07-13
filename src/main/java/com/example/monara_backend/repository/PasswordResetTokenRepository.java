package com.example.monara_backend.repository;

import com.example.monara_backend.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository <PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
