package com.loyalty.programs.model;

import lombok.Data;

@Data
public class AddAwardRequest {
    String name;
    String userId;
    String description;
    Long point;
}
