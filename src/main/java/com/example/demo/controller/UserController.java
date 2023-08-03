package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
public class UserController {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RatingRepository ratingRepository;

    // Most active User
    @GetMapping(path="/users/active")
    public @ResponseBody Optional<User> getActiveUsers() {
        final Integer activeUserId = ratingRepository.mostActiveUser();
        return userRepository.findById(activeUserId);
    }

    /*
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
     */

}