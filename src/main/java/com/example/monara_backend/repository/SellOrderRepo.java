package com.example.monara_backend.repository;

import com.example.monara_backend.model.Sell_Oder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellOrderRepo extends JpaRepository<Sell_Oder,Integer> {
}
