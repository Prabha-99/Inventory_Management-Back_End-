package com.example.monara_backend.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MonaraUtility {
    public MonaraUtility() {
    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }
}
