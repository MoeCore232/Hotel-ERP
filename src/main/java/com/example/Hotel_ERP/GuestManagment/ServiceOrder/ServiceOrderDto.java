package com.example.Hotel_ERP.GuestManagment.ServiceOrder;

import com.example.Hotel_ERP.Booking.Reservation.Reservation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class ServiceOrderDto {

    public record CreateServiceOrder (
            UUID reservation,
            @NotNull(message = "is required!")
            HotelService hotelService,
            @NotNull(message = "is required!")
            int quantity,
            @Positive(message = "is required!")
            BigDecimal totalPrice
    ) {}

    public record UpdateServiceOrder (
            UUID ServiceOrderId,
            @NotNull(message = "is required!")
            HotelService hotelService,
            @NotNull(message = "is required!")
            int quantity,
            @Positive(message = "is required!")
            BigDecimal totalPrice
    ) {}
}
