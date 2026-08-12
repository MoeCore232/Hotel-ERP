package com.example.Hotel_ERP.Accommodation.Room;

import com.example.Hotel_ERP.Accommodation.RoomStatus.RoomStatus;
import com.example.Hotel_ERP.Accommodation.RoomType.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

    @Column(name = "floor", nullable = false)
    private int floor;

    @Column(name = "caption", nullable = false)
    private int caption;

    @Column(name = "room_status", nullable = false)
    private RoomStatus roomStatus;

    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Room createRoom (RoomDto.CreateRoom createRoom) {
        Room room = new Room();
        room.roomNumber = createRoom.roomNumber();
        room.pricePerNight = createRoom.pricePerNight();
        room.floor = createRoom.floor();
        room.caption = createRoom.caption();
        room.roomStatus = RoomStatus.AVAILABLE;
        room.roomType = createRoom.roomType();
        return room;
    }

    public void setRoomStatus (RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public void checkDate () {

    }

}
