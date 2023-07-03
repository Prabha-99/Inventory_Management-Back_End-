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

    public List <PurchaseForecastingDto> getAllGrnData(String timeDuration, String category, String status) {

        List <PurchaseForecastingDto> grnList = new ArrayList < > ();

        try {
            if (timeDuration.equals("monthly") && category.equals("")) {
                grnList = forecastingRepo.getMonthlyGRN();
            } else if(timeDuration.equals("monthly") && !category.equals("")) {
                grnList = forecastingRepo.getGrnDataByMonthlyAndCategory(category);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return grnList;
    }
}
