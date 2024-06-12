package com.wipro.app.controller;

import com.wipro.app.model.User;
import com.wipro.app.payload.ApiResponse;
import com.wipro.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            User createUser = this.userService.registerUser(user);
            return new ResponseEntity<>(createUser , HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse("username already exists." , false , 400) , HttpStatus.BAD_REQUEST);
        }
    }
}
