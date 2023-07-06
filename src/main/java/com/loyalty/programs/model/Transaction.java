package com.loyalty.programs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "transacions")
@Entity
public class Transaction {
    @Id
    String id;
    String type;
    String userId;
    Long point;
    String giftCardId;
    Date createdAt = new Date();
    Date updatedAt = new Date();
}
