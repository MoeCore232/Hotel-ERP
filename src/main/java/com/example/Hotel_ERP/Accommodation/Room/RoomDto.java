package com.example.Hotel_ERP.Accommodation.Room;

import com.example.Hotel_ERP.Accommodation.RoomType.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class RoomDto {

    public record CreateRoom (
         @NotBlank(message = "is required!")
         String roomNumber,
         @Positive(message = "is required!")
         BigDecimal pricePerNight,
         @NotNull(message = "is required!")
         int floor,
         @NotNull(message = "is required!")
         int caption,
         @NotNull(message = "is required!")
         RoomType roomType
    ) {}

    public record UpdateRoom (
            UUID roomId,
            @NotBlank(message = "is required!")
            String roomNumber,
            @Positive(message = "is required!")
            BigDecimal pricePerNight,
            @NotNull(message = "is required!")
            int floor,
            @NotNull(message = "is required!")
            int caption,
            @NotNull(message = "is required!")
            RoomType roomType
    ) {}
}
