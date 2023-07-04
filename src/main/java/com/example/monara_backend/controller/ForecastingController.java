package com.example.monara_backend.controller;

import com.example.monara_backend.dto.PurchaseForecastingDto;
import com.example.monara_backend.dto.ResponseDto;
import com.example.monara_backend.dto.SellForecastingDto;
import com.example.monara_backend.model.FilterForecastingData;
import com.example.monara_backend.service.PurchaseForecastingService;
import com.example.monara_backend.service.SellForecastingService;
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
    private PurchaseForecastingService forecastingService;

    @Autowired
    private ResponseDto responseDto;

    @Autowired
    private SellForecastingService sellForecastingService;

    @PostMapping(value = "/grn")
    private ResponseEntity getGrnData(@RequestBody FilterForecastingData filterForecastingData)
    {
        List<PurchaseForecastingDto> grnList = forecastingService.getAllGrnData(
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

    @PostMapping(value = "/gin")
    private ResponseEntity getGinData(@RequestBody FilterForecastingData filterForecastingData)
    {
        List<SellForecastingDto> ginList = sellForecastingService.getAllGinData(
                filterForecastingData.getTimeDuration(),
                filterForecastingData.getCategory(),
                filterForecastingData.getStatus()
        );

        System.out.println("List: " + ginList);
        responseDto.setCode(VarList.RSP_SUCCESS);
        responseDto.setMessage("success");
        responseDto.setContent(ginList);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
