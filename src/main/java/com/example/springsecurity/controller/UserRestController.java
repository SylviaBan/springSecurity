package com.example.springsecurity.controller;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRepository userRepo;

    public UserRestController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        Optional<User> userID = userRepo.findById(id);
        if ((userID.isEmpty())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return userID;
    }
}
