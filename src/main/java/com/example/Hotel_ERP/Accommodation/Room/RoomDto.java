package com.example.Hotel_ERP.Accommodation.Room;

import com.example.Hotel_ERP.Accommodation.RoomType.RoomType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RoomDto {

    public record CreateRoom (
         String roomNumber,
         BigDecimal pricePerNight,
         int floor,
         int caption,
         LocalDateTime createdAt,
         RoomType roomType
    ) {}

}
