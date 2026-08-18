package com.example.Hotel_ERP.Accommodation.Room;

import com.example.Hotel_ERP.Accommodation.RoomType.RoomType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RoomDto {

    public record CreateRoom (
         String roomNumber,
         BigDecimal pricePerNight,
         int floor,
         int caption,
         RoomType roomType
    ) {}

    public record UpdateRoom (
            UUID roomId,
            String roomNumber,
            BigDecimal pricePerNight,
            int floor,
            int caption,
            RoomType roomType
    ) {}
}
