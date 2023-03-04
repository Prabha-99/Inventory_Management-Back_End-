package com.example.monara_backend.controllerImpl;

import com.example.monara_backend.constants.MonaraConstants;
import com.example.monara_backend.controller.UserController;
import com.example.monara_backend.serviceImpl.UserServiceImpl;
import com.example.monara_backend.utility.MonaraUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    UserServiceImpl userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MonaraUtility.getResponseEntity(MonaraConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
