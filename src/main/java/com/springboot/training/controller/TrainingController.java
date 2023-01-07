package com.springboot.training.controller;

import com.springboot.training.domain.User;
import com.springboot.training.exception.InvalidUpdateDataException;
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

    @PutMapping("updateUser/{userId}")
    public String updateUser(@PathVariable("userId") String userId,@RequestBody User updateUser) throws InvalidUpdateDataException {
        updateUser.setId(Integer.parseInt(userId));
        System.out.println(updateUser.getId());
        System.out.println(updateUser.getName());
        System.out.println(updateUser.getPassword());
        System.out.println(updateUser.getEmail());

        return service.updateUser(updateUser);
    }

    @DeleteMapping("deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        int intUserId = Integer.parseInt(userId);
        System.out.println("Requested User Id for Deletion. User Id : " + userId);
        return service.deleteUserById(intUserId);
    }
//CRUD

}
