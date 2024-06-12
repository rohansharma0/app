package com.wipro.app.service.impl;

import com.wipro.app.exception.ResourceNotFoundException;
import com.wipro.app.model.User;
import com.wipro.app.repository.UserRepository;
import com.wipro.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        String hashPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPwd);
        user.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user, String username) {
        User savedUser = this.userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User" , "username" , username));
        savedUser.setUsername(user.getUsername());
        savedUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User" , "username" , username));
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteUser(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User" , "username" , username));
        this.userRepository.delete(user);
    }
}
