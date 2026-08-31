package com.example.Hotel_ERP.GuestManagment.Guest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class GuestDto {

    public record CreateGuest (
            @NotBlank(message = "is required!")
            String firstName,
            @NotBlank(message = "is required!")
            String lastName,
            @Email(message = "is required!")
            String email,
            @NotNull(message = "is required!")
            LocalDate birthDate
    ) {}
}
