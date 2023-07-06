package com.loyalty.programs.controller;

import com.loyalty.programs.model.RedeemRewardRequest;
import com.loyalty.programs.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllTransactions(@PathVariable String userId) {
        return new ResponseEntity<>(transactionService.getAllTransactions(userId), HttpStatus.OK);
    }
}
