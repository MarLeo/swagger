package com.tchokonthe.swagger.service;

import com.tchokonthe.swagger.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<User> users;

    static {
        users = populate();
    }

    @Override
    public User findById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public User findByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public User saveUser(User user) {
        if (!users.contains(user)) {
            user.setId(counter.incrementAndGet());
            users.add(user);
            return user;
        }
        else return null;
    }

    @Override
    public User updateUser(User user, long id) {
        User currentUser = findById(id);
      return currentUser != null ? users.set(users.indexOf(currentUser), user) : null;
    }

    @Override
    public void deleteById(long id) {
        users.remove(users.stream()
                          .filter(user -> user.getId() == id)
                          .findAny().get());
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }

    @Override
    public void deleteAllUsers() {
        users.clear();
    }


    private static List<User> populate() {
        List<User> users = new ArrayList<>();
        users.add(new User(counter.incrementAndGet(),"Sam",30, 70000));
        users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
        users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
        users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000));
        return users;
    }


}
