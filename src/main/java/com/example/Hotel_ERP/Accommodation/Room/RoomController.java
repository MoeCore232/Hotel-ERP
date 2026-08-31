package com.example.Hotel_ERP.Accommodation.Room;

import com.example.Hotel_ERP.Booking.Reservation.ReservationDto;
import com.example.Hotel_ERP.GuestManagment.Payment.Payment;
import com.example.Hotel_ERP.Shared.ErrorHandling.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/get-all-rooms")
    public ResponseEntity<GlobalResponse<List<Room>>> getAllRooms () {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(new GlobalResponse<>(rooms), HttpStatus.OK);
    }

    @GetMapping("/get-room-by-id/{roomId}")
    public ResponseEntity<GlobalResponse<Room>> getRoomById (@PathVariable UUID roomId) {
        Room room = roomService.getRoomById(roomId);
        return new ResponseEntity<>(new GlobalResponse<>(room), HttpStatus.OK);
    }

    @PostMapping("/create-room")
    public ResponseEntity<GlobalResponse<String>> createRoom (@Valid @RequestBody RoomDto.CreateRoom createRoom) {
        roomService.createRoom(createRoom);
        return new ResponseEntity<>(new GlobalResponse<>("Room created successful"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-room/{roomId}")
    public ResponseEntity<GlobalResponse<String>> deleteRoom (@PathVariable UUID roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(new GlobalResponse<>("Room deleted successful"), HttpStatus.OK);
    }

    @PutMapping("/update-room")
    public ResponseEntity<GlobalResponse<String>> updateRoom (@Valid @RequestBody RoomDto.UpdateRoom updateRoom) {
        roomService.updateRoon(updateRoom);
        return new ResponseEntity<>(new GlobalResponse<>("Room updated successfully!"), HttpStatus.OK);
    }
}
