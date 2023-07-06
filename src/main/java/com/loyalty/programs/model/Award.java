package com.loyalty.programs.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "awards")
@Entity
public class Award {
    @Id
    String id;
    String name;
    String userId;
    String description;
    Long point;
    Date createdAt = new Date();
    Date updatedAt = new Date();
}