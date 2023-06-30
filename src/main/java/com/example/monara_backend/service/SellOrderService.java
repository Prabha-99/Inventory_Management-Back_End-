package com.example.monara_backend.service;

import com.example.monara_backend.dto.PurchaseOrderDto;
import com.example.monara_backend.dto.SellOrderDto;
import com.example.monara_backend.model.Purchase_Oder;
import com.example.monara_backend.model.Sell_Oder;
import com.example.monara_backend.repository.SellOrderRepo;
import com.example.monara_backend.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SellOrderService {

    @Autowired
    private SellOrderRepo sellOrderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveSellOrder(SellOrderDto sellOrderDto){
        //Check the data exist or not in the database
        if(sellOrderRepo.existsById(sellOrderDto.getSellOrder_id())){
            return VarList.RSP_DUPLICATED;
        }else{
            sellOrderRepo.save(modelMapper.map(sellOrderDto, Sell_Oder.class));
            return VarList.RSP_SUCCESS;
        }

    }
}
