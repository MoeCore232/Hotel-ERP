package com.example.Hotel_ERP.Accommodation.Room;

import com.example.Hotel_ERP.Accommodation.RoomType.RoomType;
import com.example.Hotel_ERP.GuestManagment.Payment.Payment;
import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {

    private final static Logger logger = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RoomRepo roomRepo;

    public List<Room> getAllRooms () {
        return roomRepo.findAll();
    }

    public Room getRoomById (UUID roomId) {
        Room findRoom = roomRepo.findById(roomId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(roomId));
        return findRoom;
    }

    public void createRoom (RoomDto.CreateRoom createRoom) {
        if (!RoomType.isValid(createRoom.roomType().name())) {
            throw CustomResponseException.publicError("Invalid room type", 400);
        }
        Room room = Room.createRoom(createRoom);
        roomRepo.save(room);
    }

    public void deleteRoom (UUID roomId) {
        Room findRoom = roomRepo.findById(roomId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(roomId));
        roomRepo.deleteById(findRoom.getId());
    }

    public void updateRoon (RoomDto.UpdateRoom updateRoom) {
        Room findRoom = roomRepo.findById(updateRoom.roomId())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(updateRoom.roomId()));

        Room room = Room.updateRoom(findRoom, updateRoom);
        roomRepo.save(room);
    }
}

