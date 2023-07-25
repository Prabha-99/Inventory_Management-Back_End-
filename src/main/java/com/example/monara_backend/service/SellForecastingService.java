package com.example.monara_backend.service;

import com.example.monara_backend.dto.PurchaseForecastingDto;
import com.example.monara_backend.dto.SellForecastingDto;
import com.example.monara_backend.repository.SellForecastingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellForecastingService {
    @Autowired
    private SellForecastingRepo sellForecastingRepo;
    public List<SellForecastingDto> getAllGinData(String timeDuration, String category) {

        // Create an empty list to store the result
        List <SellForecastingDto> ginList = new ArrayList<>();

        try {
            // Check if a specific category is provided to determine which query to call
            if (category.equals("")) {
                // If no specific category is provided, retrieve GIN data for all categories
                ginList = sellForecastingRepo.getMonthlyGIN();
            } else if( !category.equals("")) {
                // If a specific category is provided, retrieve GIN data for that category
                ginList = sellForecastingRepo.getGinDataByMonthlyAndCategory(category);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return ginList;
    }
}
