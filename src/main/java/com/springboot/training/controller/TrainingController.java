package com.springboot.training.controller;

import com.springboot.training.domain.User;
import com.springboot.training.exception.InvalidUserException;
import com.springboot.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainingController {
    @Autowired
    private TrainingService service;
    @GetMapping("/getUsers")
    public List<User> getUsers(){

        return service.getUsers();
    }

    @PostMapping("/createUser")
    public String create(@RequestBody User userData){
        System.out.println(userData.getId());
        System.out.println(userData.getName());
        System.out.println(userData.getPassword());
        System.out.println(userData.getEmail());
        return service.createUser(userData);
    }

    @GetMapping("getUser/{userId}") //getUser/3
    public User getUser(@PathVariable("userId") String userId) throws InvalidUserException {
        int userIdValue = Integer.parseInt(userId);
        System.out.println("Received User Id : " + userIdValue);
        return service.getUserById(userIdValue);
    }

//CRUD

}
