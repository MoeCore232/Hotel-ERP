package com.example.Hotel_ERP.Booking.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ReservationDto {

    public record CreateReservation (
            UUID guestId,
            UUID roomId,

            LocalDate checkInDate,
            LocalDate checkOutDate,

            int numberOfGuest,
            BigDecimal totalPrice
    ) {}

}
