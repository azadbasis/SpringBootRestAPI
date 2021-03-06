package com.restfulapi.app.ws.ui.controller;

import com.restfulapi.app.ws.service.UserService;
import com.restfulapi.app.ws.shared.dto.UserDto;
import com.restfulapi.app.ws.ui.model.request.UserDetailsRequestModel;
import com.restfulapi.app.ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {

        UserRest returnValue=new UserRest();
        UserDto userDto=userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto,returnValue);


        return returnValue;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

        UserRest returnValue=new UserRest();
        UserDto userDto=new UserDto();

        BeanUtils.copyProperties(userDetails,userDto);
        UserDto createUser=userService.createUser(userDto);
        BeanUtils.copyProperties(createUser,returnValue);

        return returnValue;
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }
}
