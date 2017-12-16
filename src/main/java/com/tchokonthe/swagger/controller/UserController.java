package com.tchokonthe.swagger.controller;


import com.tchokonthe.swagger.error.CustomErrorType;
import com.tchokonthe.swagger.model.User;
import com.tchokonthe.swagger.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "User Controller", description = "manage user data", tags = "User API")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "Returns All Users", response = User.class)
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {

        List<User> users = userService.findAllUsers();

        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns A Single User By id", response = User.class)
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id){

        User user = userService.findById(id);

        return user == null ? new ResponseEntity<>(new CustomErrorType("User with id " + id
                + " not found"), HttpStatus.NOT_FOUND) : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new User", response = User.class)
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user){

        User findUser = userService.saveUser(user);

        return findUser == null ?
               new ResponseEntity<>(new CustomErrorType("Unable to create. A User with name " +
                user.getName() + " already exist."), HttpStatus.CONFLICT)
                : new ResponseEntity<>(findUser, HttpStatus.CREATED) ;
    }


    @ApiOperation(value = "Update a User", response = User.class)
    @PutMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") long id) {

        User updateUser = userService.updateUser(user, id);

        return updateUser  == null ? new ResponseEntity<>(new CustomErrorType("Unable to upate. User with id " + user.getId() + " not found."),
                HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a User", response = User.class)
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

        User user = userService.findById(id);

        ResponseEntity responseEntity;

        if (user == null) {
           responseEntity = new ResponseEntity<>(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        } else {
            userService.deleteById(id);
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Delete all Users", response = User.class)
    @DeleteMapping(value = "/user")
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
