package com.marin.usersms.ui.controller;

import com.marin.usersms.service.UserService;
import com.marin.usersms.shared.UserDto;
import com.marin.usersms.ui.model.CreateUserRequestModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;


    @GetMapping("/status")
    public String status() {
        return "Users Microservice is working.";
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel userModel) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userModel, UserDto.class);
        userService.createUser(userDto);

        return "CREATE USER WORKING";
    }

}
