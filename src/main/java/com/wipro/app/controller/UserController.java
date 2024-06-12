package com.wipro.app.controller;

import com.wipro.app.model.User;
import com.wipro.app.payload.ApiResponse;
import com.wipro.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/user/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user , @PathVariable String username){
        User updateUser = this.userService.updateUser(user , username);
        return new ResponseEntity<>(updateUser , HttpStatus.OK);
    }

    @DeleteMapping("/user/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String username){
        this.userService.deleteUser(username);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully" , true , 200) , HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }
}
