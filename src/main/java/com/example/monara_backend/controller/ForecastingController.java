package com.example.monara_backend.controller;

import com.example.monara_backend.dto.ForecastingDto;
import com.example.monara_backend.dto.ResponseDto;
import com.example.monara_backend.model.FilterForecastingData;
import com.example.monara_backend.model.GRN;
import com.example.monara_backend.repository.UserRepo;
import com.example.monara_backend.service.ForecastingService;
import com.example.monara_backend.service.NotificationService;
import com.example.monara_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/forecasting")
public class ForecastingController {

    @Autowired
    private ForecastingService forecastingService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/grn")
    private ResponseEntity getGrnData(@RequestBody FilterForecastingData filterForecastingData)
    {
        List<ForecastingDto> grnList = forecastingService.getAllGrnData(
                filterForecastingData.getTimeDuration(),
                filterForecastingData.getCategory(),
                filterForecastingData.getStatus()
        );

        System.out.println("List: " + grnList);
        responseDto.setCode(VarList.RSP_SUCCESS);
        responseDto.setMessage("success");
        responseDto.setContent(grnList);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
