package com.marin.usersms.ui.controller;

import com.marin.usersms.service.UserService;
import com.marin.usersms.shared.UserDto;
import com.marin.usersms.ui.model.request.CreateUserRequestModel;
import com.marin.usersms.ui.model.response.CreateUserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userModel) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userModel, UserDto.class);
        UserDto updatedModel = userService.createUser(userDto);

        CreateUserResponseModel responseModel = modelMapper.map(updatedModel, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

}
