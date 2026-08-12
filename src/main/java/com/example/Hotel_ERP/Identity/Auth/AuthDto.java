package com.example.Hotel_ERP.Identity.Auth;

import java.util.UUID;

public class AuthDto {

    public record SighUp (
            UUID userId,
            String username,
            String password
    ) { }

    public record Login (
            String username,
            String password
    ) { }

     public record LoginResponse (
            String token,
            UUID userId,
            String message
    ) {}
}
