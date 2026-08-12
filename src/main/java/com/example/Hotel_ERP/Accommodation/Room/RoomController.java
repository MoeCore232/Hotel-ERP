package com.example.Hotel_ERP.Accommodation.Room;

import com.example.Hotel_ERP.Shared.ErrorHandling.GlobalResponse;
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

    @PostMapping("/create-room")
    public ResponseEntity<GlobalResponse<String>> createRoom (@RequestBody RoomDto.CreateRoom createRoom) {
        roomService.createRoom(createRoom);
        return new ResponseEntity<>(new GlobalResponse<>("Room created successful"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-room/{roomId}")
    public ResponseEntity<GlobalResponse<String>> deleteRoom (@PathVariable UUID roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(new GlobalResponse<>("Room deleted successful"), HttpStatus.OK);
    }

}
