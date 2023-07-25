package com.example.monara_backend.service;

import com.example.monara_backend.dto.PurchaseForecastingDto;
import com.example.monara_backend.repository.PurchaseForecastingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseForecastingService {
    @Autowired
    private PurchaseForecastingRepo forecastingRepo;

    public List <PurchaseForecastingDto> getAllGrnData(String timeDuration, String category) {
        // Create an empty list to store the result
        List <PurchaseForecastingDto> grnList = new ArrayList<> ();

        try {
            // Check if a specific category is provided to determine which query to call
            if (category.equals("")) {
                // If no specific category is provided, retrieve GRN data for all categories
                grnList = forecastingRepo.getMonthlyGRN();
            } else if(!category.equals("")) {
                // If a specific category is provided, retrieve GRN data for that category
                grnList = forecastingRepo.getGrnDataByMonthlyAndCategory(category);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return grnList;
    }
}
