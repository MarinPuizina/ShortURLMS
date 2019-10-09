package com.marin.usersms.ui.controller;

import com.marin.usersms.ui.entity.UserEntity;
import com.marin.usersms.ui.model.CreateUserRequestModel;
import com.marin.usersms.ui.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/status")
    public String status() {
        return "Users Microservice is working.";
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel userModel) {

        UserEntity returnModel = new UserEntity();
        returnModel.setFirstName("Marin");
        returnModel.setLastName("Puizina");
        returnModel.setEmail("marin@gmail.com");
        returnModel.setUserId("marin123");
        returnModel.setEncryptedPassword("ma#2323$3$o23dd");

        usersRepository.save(returnModel);

        return "CREATE USER WORKING";
    }

}
