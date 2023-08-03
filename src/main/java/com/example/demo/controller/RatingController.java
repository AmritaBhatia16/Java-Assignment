package com.example.demo.controller;

import com.example.demo.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="demo")
public class RatingController {
    @Autowired
    private RatingRepository ratingRepository;

}