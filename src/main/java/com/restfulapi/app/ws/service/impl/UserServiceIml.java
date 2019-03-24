package com.restfulapi.app.ws.service.impl;

import com.restfulapi.app.ws.UserRepository;
import com.restfulapi.app.ws.io.entity.UserEntity;
import com.restfulapi.app.ws.service.UserService;
import com.restfulapi.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(user,userEntity);

        userEntity.setEncryptedPassword("Test");
        userEntity.setUserId("TestUserId");
        UserEntity storedUserDetails=userRepository.save(userEntity);

        UserDto returnValue=new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnValue);

        return returnValue;
    }
}
