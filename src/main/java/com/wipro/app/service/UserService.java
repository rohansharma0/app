package com.wipro.app.service;

import com.wipro.app.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);

    User updateUser(User user , String username);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void deleteUser(String username);
}
