package com.springboot.training.service;

import com.springboot.training.domain.User;
import com.springboot.training.exception.InvalidUserException;
import com.springboot.training.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository repository;

    public List<User> getUsers(){
        /*List<User> listUsers = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setName("Harish");
        user1.setPassword("harish123");
        user1.setEmail("harish12@gmail.com");

        listUsers.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setName("Rishi");
        user2.setPassword("rishi123");
        user2.setEmail("rishi123@gmail.com");

        listUsers.add(user2);

        User user3 = new User();
        user3.setId(3);
        user3.setName("Sandya");
        user3.setPassword("sandya12");
        user3.setEmail("sandya12@gmail.com");

        listUsers.add(user3);*/


        try {
            return repository.allUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String createUser(User user){
        return repository.createUser(user);
    }

    public User getUserById(int userId) throws InvalidUserException {
        return repository.getUserById(userId);
    }
}
