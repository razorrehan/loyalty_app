package com.loyalty.programs.model;

import lombok.Data;

@Data
public class RedeemRewardRequest {
    String userId;
    String poshvineGiftCardId;
    String companyName;
    Long pointsValue; //point value
    String value; // value in INR
}
