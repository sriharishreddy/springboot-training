package com.springboot.training.controller;

import com.springboot.training.domain.User;
import com.springboot.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingController {
    @Autowired
    private TrainingService service;
    @GetMapping("/hello")
    public List<User> hello(){
        return service.getUsers();
    }

}
