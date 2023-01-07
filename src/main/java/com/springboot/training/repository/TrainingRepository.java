package com.springboot.training.repository;

import com.springboot.training.domain.User;
import com.springboot.training.exception.InvalidUpdateDataException;
import com.springboot.training.exception.InvalidUserException;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainingRepository {
    @Value("{spring.datasource.url}")
    private String databaseUrl;
    @Value("{spring.datasource.username}")
    private String userName;
    @Value("{spring.datasource.password}")
    private String password;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> allUsers() throws SQLException {

        try {
            return jdbcTemplate.query("SELECT * FROM harish.user", new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    int userId = rs.getInt("id");
                    String userName = rs.getString("name");
                    String password = rs.getString("password");
                    String email = rs.getString("email");

                    User user = new User();
                    user.setId(userId);
                    user.setName(userName);
                    user.setPassword(password);
                    user.setEmail(email);

                    return user;
                }
            });
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String createUser(User user){

        String insertQuery = "INSERT INTO `harish`.`user` (`id`, `name`, `password`, `email`) VALUES ('"+user.getId()+"', '"+user.getName()+"', '"+user.getPassword()+"', '"+user.getEmail()+"');";

        jdbcTemplate.execute(insertQuery);
        return "Created user Successfully..!";
    }

    public User getUserById(int userId) throws InvalidUserException {
        List<User> users = jdbcTemplate.query("SELECT * FROM harish.user WHERE id = " + userId + ";", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");

                User user = new User();
                user.setId(userId);
                user.setName(userName);
                user.setPassword(password);
                user.setEmail(email);

                return user;
            }
        });

        if(users.size() == 0){
            throw new InvalidUserException("Invalid User Id="+userId+". Please Enter Valid User Id.");
        }

        return users.get(0);

    }

    public String updateUser(User user) throws InvalidUpdateDataException {
        String updateQuery = null;

        if(StringUtils.hasText(user.getEmail()) && (!StringUtils.hasText(user.getName()) && !StringUtils.hasText(user.getPassword()))) {
            updateQuery = "UPDATE `harish`.`user` SET `email` = '" + user.getEmail() + "' WHERE (`id` = '" + user.getId() + "');";
            System.out.println("Executing Query : " + updateQuery);
        }
        else if(StringUtils.hasText(user.getName()) && (!StringUtils.hasText(user.getEmail()) && !StringUtils.hasText(user.getPassword()))) {
            updateQuery = "UPDATE `harish`.`user` SET `name` = '" + user.getName() + "' WHERE (`id` = '" + user.getId() + "');";
            System.out.println("Executing Query : " + updateQuery);
        }
        else if(StringUtils.hasText(user.getPassword()) && (!StringUtils.hasText(user.getName()) && !StringUtils.hasText(user.getEmail()))) {
            updateQuery = "UPDATE `harish`.`user` SET `password` = '" + user.getPassword() + "' WHERE (`id` = '" + user.getId() + "');";
            System.out.println("Executing Query : " + updateQuery);
        }
        else if(StringUtils.hasText(user.getEmail()) && StringUtils.hasText(user.getName()) && StringUtils.hasText(user.getPassword())) {
            updateQuery = "UPDATE `harish`.`user` SET `name` = '"+user.getName()+"', `password` = '"+user.getPassword()+"', `email` = '"+user.getEmail()+"' WHERE (`id` = '"+ user.getId() +"');";
            System.out.println("Executing Query : " + updateQuery);
        } else {
            System.out.println("Throwing an Exception..!");
            throw new InvalidUpdateDataException();
        }

        int response = jdbcTemplate.update(updateQuery);
        return  "Updated Records : "+response;
    }

    public String deleteUserByID(int userId) {
        String deleteQuery = "DELETE FROM `harish`.`user` WHERE (`id` = '"+userId+"');";
        System.out.println("Delete Query : " + deleteQuery);
        jdbcTemplate.execute(deleteQuery);
        return "Deleted Record Successfully. User Id : " + userId;
    }
}
