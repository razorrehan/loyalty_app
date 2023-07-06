package com.loyalty.programs.controller;

import com.loyalty.programs.model.RedeemRewardRequest;
import com.loyalty.programs.service.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/gift_card")
@RestController
public class GiftCardController {

    @Autowired
    GiftCardService giftCardService;

    @PostMapping("/redeem")
    public ResponseEntity<?> redeemPoints(@RequestBody RedeemRewardRequest request) {
        return new ResponseEntity<>(giftCardService.redeemReward(request), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllGiftCards(@PathVariable String userId) {
        return new ResponseEntity<>(giftCardService.getAllGiftCards(userId), HttpStatus.OK);
    }
}
