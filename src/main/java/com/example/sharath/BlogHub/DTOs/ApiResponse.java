package com.example.sharath.BlogHub.DTOs;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ApiResponse {
    String message;
    boolean status;
}
