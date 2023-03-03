package com.example.monara_backend.service;

import com.example.monara_backend.model.User;
import com.example.monara_backend.model.UserRole;
import com.example.monara_backend.repository.RoleRepo;
import com.example.monara_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class userService implements UserServiceInter{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    //Creating User
    @Override
    public User createUser(User user, Set<UserRole> userRole) throws Exception {
        User local = this.userRepo.findByUserName(user.getUser_name());
        if(local!= null){
            System.out.println("User is already there!!");
            throw new Exception("User already present!");

        }
        else{
            //create User
            for (UserRole ur:userRole) {
                roleRepo.save(ur.getRole());
            }
            user.getUserRole().addAll(userRole);
            local=this.userRepo.save(user);
        }
        return local;
    }
}
