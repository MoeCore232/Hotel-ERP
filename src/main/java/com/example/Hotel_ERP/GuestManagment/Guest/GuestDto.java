package com.example.Hotel_ERP.GuestManagment.Guest;

import java.time.LocalDate;

public class GuestDto {

    public record CreateGuest (
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate
    ) {}
}
