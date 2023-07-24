package com.example.monara_backend.controller;

import com.example.monara_backend.model.User;
import com.example.monara_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
//    public ResponseEntity <List<Map<String, Object>>> getAllUser(){
//        return new ResponseEntity<>(userService.getAllUsers() , HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        if(user == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return  new ResponseEntity<>(user,HttpStatus.OK);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.updateUser(user) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id){
        return  new ResponseEntity<HttpStatus>(userService.deleteUser(id));
    }


}
