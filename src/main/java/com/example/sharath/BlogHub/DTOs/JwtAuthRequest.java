package com.example.sharath.BlogHub.DTOs;

import lombok.Data;

@Data
public class JwtAuthRequest {

    public String userName;

    public String password;
}
