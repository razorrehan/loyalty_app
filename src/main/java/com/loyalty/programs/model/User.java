package com.loyalty.programs.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    private String id;
    String name;
    String contact;
    Long total_points;
    Date createdAt = new Date();
    Date updatedAt = new Date();
}
