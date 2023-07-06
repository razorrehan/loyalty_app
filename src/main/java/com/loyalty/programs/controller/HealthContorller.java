package com.loyalty.programs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthContorller {

    @GetMapping("/")
    public ResponseEntity<?> getHealth() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
