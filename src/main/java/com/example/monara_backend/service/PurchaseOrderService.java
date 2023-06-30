package com.example.monara_backend.service;


import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.dto.PurchaseOrderDto;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.Purchase_Oder;
import com.example.monara_backend.repository.PurchaseOrderRepo;
import com.example.monara_backend.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String savePurchaseOrder(PurchaseOrderDto purchaseOrderDto){
        //Check the data exist or not in the database
        if(purchaseOrderRepo.existsById(purchaseOrderDto.getPurchaseOrder_id())){
            return VarList.RSP_DUPLICATED;
        }else{
            purchaseOrderRepo.save(modelMapper.map(purchaseOrderDto, Purchase_Oder.class));
            return VarList.RSP_SUCCESS;
        }

    }
}
