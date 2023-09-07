package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserControllerTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetActiveUsers() throws Exception {
        final Integer userId = 1, userAge = 20, userZip = 111111;
        final String userGender = "Female", userPick = "business";
        User user = new User();
        user.setUserId(userId);
        user.setAge(userAge);
        user.setGender(userGender);
        user.setPick(userPick);
        user.setZip(userZip);

        when(ratingRepository.mostActiveUserId()).thenReturn(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userController.getActiveUserId();

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getUserId());
        assertEquals(20, result.get().getAge());
        assertEquals("Female", result.get().getGender());
        assertEquals("business", result.get().getPick());
        assertEquals(111111, result.get().getZip());

    }

}