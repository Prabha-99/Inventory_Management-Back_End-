package com.example.monara_backend.serviceImpl;

import com.example.monara_backend.DTO.UserDto;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import com.example.monara_backend.service.UserService;
import com.example.monara_backend.utility.MonaraUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j //for login purpose
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public String saveUser(UserDto userDto) {
        if (userRepo.existsById(userDto.getUser_id())){
            return MonaraUtil.RSP_DUPLICATED;
        }else {
            userRepo.save(modelMapper.map(userDto, User.class));
            return MonaraUtil.RSP_SUCCESS;
        }
    }
}
