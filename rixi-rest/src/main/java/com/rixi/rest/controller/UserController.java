package com.rixi.rest.controller;

import com.rixi.rest.model.User;
import com.rixi.rest.service.UserRepository;
import com.rixi.rest.service.UserStatisticServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStatisticServiceClient userStatisticServiceClient;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        user.setCreateDate(new Date());
        userStatisticServiceClient.increaseCount("creates", System.currentTimeMillis());
        return userRepository.insert(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        userStatisticServiceClient.increaseCount("updates", System.currentTimeMillis());
        return userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable String id) {
        return userRepository.findById(id).get();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        userStatisticServiceClient.increaseCount("views", System.currentTimeMillis());
        return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userStatisticServiceClient.increaseCount("deletes", System.currentTimeMillis());
        userRepository.deleteById(id);
    }

}
