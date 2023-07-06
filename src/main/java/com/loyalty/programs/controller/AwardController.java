package com.loyalty.programs.controller;

import com.loyalty.programs.model.AddAwardRequest;
import com.loyalty.programs.model.CreateUserRequest;
import com.loyalty.programs.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/awards")
public class AwardController {

    @Autowired
    AwardService awardService;

    @PostMapping("/add")
    public ResponseEntity<?> addAward(@RequestBody AddAwardRequest request) {
        return new ResponseEntity<>(awardService.addAward(request), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllAwards(@PathVariable String userId) {
        return new ResponseEntity<>(awardService.getAllAwards(userId), HttpStatus.OK);
    }
}
