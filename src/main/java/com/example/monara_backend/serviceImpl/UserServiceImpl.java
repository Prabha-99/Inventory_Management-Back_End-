package com.example.monara_backend.serviceImpl;

import com.example.monara_backend.constants.MonaraConstants;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import com.example.monara_backend.service.UserService;
import com.example.monara_backend.utility.MonaraUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j //for login purpose
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) { //user_name change that place
        log.info("inside signup {}",requestMap);
        try {


        if(validateSingUpMap(requestMap)){
            User user = userRepo.findByEmailId(requestMap.get("user_email"));
            if(Objects.isNull(user)){
                userRepo.save(getUserFromMap(requestMap));
                return MonaraUtility.getResponseEntity("Successfully Registered!!",HttpStatus.OK);
            }
            else{
                return MonaraUtility.getResponseEntity("Email Already Exits",HttpStatus.BAD_REQUEST); //MonaraConstants
            }
        }
        else {
            return MonaraUtility.getResponseEntity(MonaraConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }} catch (Exception e) {
            e.printStackTrace();
        }
        return MonaraUtility.getResponseEntity(MonaraConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSingUpMap(Map<String,String> requestMap){
        if(requestMap.containsKey("user_first_name") && requestMap.containsKey("user_last_name") &&
                requestMap.containsKey("user_name") && requestMap.containsKey("user_email") && requestMap.containsKey("user_password")){
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String,String> requestMap){
        User user = new User();
        user.setUser_first_name(requestMap.get("user_first_name"));
        user.setUser_first_name(requestMap.get("user_last_name"));
        user.setUser_name(requestMap.get("user_name"));
        user.setUser_email(requestMap.get("user_email"));
        user.setUser_password(requestMap.get("user_password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
