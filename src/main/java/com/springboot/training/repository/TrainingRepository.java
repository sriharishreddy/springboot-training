package com.springboot.training.repository;

import com.springboot.training.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
}
