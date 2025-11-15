package com.newsy.newsy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class SubscribeRequest {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private List<String> categories;
}

