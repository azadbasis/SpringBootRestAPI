package com.restfulapi.app.ws.service.impl;

import com.restfulapi.app.ws.UserRepository;
import com.restfulapi.app.ws.io.entity.UserEntity;
import com.restfulapi.app.ws.service.UserService;
import com.restfulapi.app.ws.shared.Utils;
import com.restfulapi.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {

        if(userRepository.findByEmail(user.getEmail())!=null)throw new RuntimeException("Record already exists");

        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(user,userEntity);

        String publicUserId=utils.generateUserId(30);
        userEntity.setUserId(publicUserId);

        userEntity.setEncryptedPassword("Test");

        UserEntity storedUserDetails=userRepository.save(userEntity);

        UserDto returnValue=new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnValue);

        return returnValue;
    }
}
