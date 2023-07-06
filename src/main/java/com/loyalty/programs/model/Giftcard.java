package com.loyalty.programs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "gift_cards")
public class Giftcard {
    @Id
    String Id;
    String companyName;
    Long value;
    String poshvineGiftCardId;
    String userId;
    String couponCode;
    Date createdAt = new Date();
    Date updatedAt = new Date();
    Date purchasedAt = new Date();
    Date expiredAt = new Date();
}
