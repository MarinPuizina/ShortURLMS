package com.marin.usersms.ui.controller;

import com.marin.usersms.ui.model.CreateUserRequestModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status")
    public String status() {
        return "Users Microservice is working.";
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel userModel) {

        return "CREATE USER WORKING";
    }

}
