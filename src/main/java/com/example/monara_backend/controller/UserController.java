package com.example.monara_backend.controller;


import com.example.monara_backend.DTO.ResponseDto;
import com.example.monara_backend.DTO.UserDto;
import com.example.monara_backend.model.User;
import com.example.monara_backend.service.UserService;
import com.example.monara_backend.utility.MonaraUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ResponseDto responseDto;

    @PostMapping(value = "/signup")
    public ResponseEntity saveEmployee(@RequestBody UserDto userDto){
        try {
            String res= userService.saveUser(userDto);
            if (res.equals("00")){
                responseDto.setCode(MonaraUtil.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(userDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else if(res.equals("06")) {
                responseDto.setCode(MonaraUtil.RSP_DUPLICATED);
                responseDto.setMessage("Employee Registered");
                responseDto.setContent(userDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else {
                responseDto.setCode(MonaraUtil.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            responseDto.setCode(MonaraUtil.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
