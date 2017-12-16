package com.tchokonthe.swagger.service;

import com.tchokonthe.swagger.model.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    User findByName(String name);

    User saveUser(User user);

    User updateUser(User user, long id);

    void deleteById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();
}
