package com.example.Hotel_ERP.Booking.Reservation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ReservationDto {

    public record CreateReservation (
            UUID guestId,
            UUID roomId,
            @NotNull(message = "is required!")
            LocalDate checkInDate,
            @NotNull(message = "is required!")
            LocalDate checkOutDate,
            @NotNull(message = "is required!")
            int numberOfGuest,
            @Positive(message = "is required!")
            BigDecimal totalPrice
    ) {}

    public record UpdateReservation (
            UUID reservationId,
            @NotNull(message = "is required!")
            LocalDate checkInDate,
            @NotNull(message = "is required!")
            LocalDate checkOutDate,
            @NotNull(message = "is required!")
            int numberOfGuest,
            @Positive(message = "is required!")
            BigDecimal totalPrice
    ) {}
}
