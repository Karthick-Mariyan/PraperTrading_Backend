package com.stera.papertrade.controller;

import java.util.Optional;

import com.stera.papertrade.model.Auth.AuthenticationResponse;
import com.stera.papertrade.serviceinterface.UserProfileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.stera.papertrade.model.User;
import com.stera.papertrade.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserProfileInterface userProfileService;

    @PostMapping("/register")
    public AuthenticationResponse registerUser(@RequestBody User entity) {
        return userProfileService.registerUser(entity);
        
    }

    @GetMapping("/getuser/{userId}")
    public Optional<User> getAllUsers(@PathVariable String userId){
        return userProfileService.loadUserByUserID(userId);
    }
    
}
