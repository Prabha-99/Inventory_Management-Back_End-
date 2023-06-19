package com.example.monara_backend.repository;

import com.example.monara_backend.dto.ShowroomFileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowroomRepo extends JpaRepository<ShowroomFileDocument,Long> {
}
