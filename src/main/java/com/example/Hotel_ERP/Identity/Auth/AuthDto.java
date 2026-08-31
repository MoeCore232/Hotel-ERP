package com.example.Hotel_ERP.Identity.Auth;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class AuthDto {

    public record SighUp (
            UUID userId,
            @NotBlank(message = "is required!")
            String username,
            @NotBlank(message = "is required!")
            String password
    ) { }

    public record Login (
            @NotBlank(message = "is required!")
            String username,
            @NotBlank(message = "is required!")
            String password
    ) { }

     public record LoginResponse (
            String token,
            UUID userId,
            String message
    ) {}
}
