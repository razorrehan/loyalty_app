package com.loyalty.programs.controller;

import com.loyalty.programs.model.CreateUserRequest;
import com.loyalty.programs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> getUser(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
