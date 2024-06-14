package com.wipro.app.controller;

import com.wipro.app.model.Role;
import com.wipro.app.model.User;
import com.wipro.app.payload.ApiRequest;
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
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String username){
        if(this.userService.isUserExist(user.getUsername())){
            return new ResponseEntity<>(new ApiResponse("username already exists." , false , 400) , HttpStatus.BAD_REQUEST);
        }
        User updateUser = this.userService.updateUser(user , username);
        return new ResponseEntity<>(updateUser , HttpStatus.OK);
    }

    @PutMapping("/user/{username}/role")
    public ResponseEntity<?> updateRole(@RequestBody ApiRequest<Role> request , @PathVariable String username){
        if(!this.userService.isUserExist(username)){
            return new ResponseEntity<>(new ApiResponse("username is not present." , true , 400) , HttpStatus.BAD_REQUEST);
        }
        User updateUser = this.userService.updateRole(username , request.getData());
        return new ResponseEntity<>(updateUser , HttpStatus.OK);
    }

    @PutMapping("/user/{username}/password")
    public ResponseEntity<?> updatePassword(@RequestBody ApiRequest<String> request , @PathVariable String username){
        if(!this.userService.isUserExist(username)){
            return new ResponseEntity<>(new ApiResponse("username is not present." , true , 400) , HttpStatus.BAD_REQUEST);
        }
        User updateUser = this.userService.updatePassword(username , request.getData());
        return new ResponseEntity<>(updateUser , HttpStatus.OK);
    }

    @DeleteMapping("/user/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String username){
        if(!this.userService.isUserExist(username)){
            return new ResponseEntity<>(new ApiResponse("username is not present." , true , 400) , HttpStatus.BAD_REQUEST);
        }
        this.userService.deleteUser(username);
        return new ResponseEntity<>(new ApiResponse("user deleted successfully" , true , 200) , HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //TODO: select query is calling multiple times - FIX ME
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        if(!this.userService.isUserExist(username)){
            return new ResponseEntity<>(new ApiResponse("username is not present." , true , 400) , HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }
}
