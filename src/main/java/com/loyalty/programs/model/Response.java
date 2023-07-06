package com.loyalty.programs.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class Response {
    HttpStatus status;
    String description;
    Object response;
}
