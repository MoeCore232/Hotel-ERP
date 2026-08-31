package com.example.Hotel_ERP.GuestManagment.Guest;

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
@RequestMapping("/api/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/get-all-guests")
    public ResponseEntity<GlobalResponse<List<Guest>>> getAllGuests () {
        List<Guest> guests = guestService.getAllGuests();
        return new ResponseEntity<>(new GlobalResponse<>(guests), HttpStatus.OK);
    }

    @GetMapping("/get-guest-by-id/{guestId}")
    public ResponseEntity<GlobalResponse<Guest>> getGuestById (@PathVariable UUID guestId) {
        Guest guest = guestService.getGuestById(guestId);
        return new ResponseEntity<>(new GlobalResponse<>(guest), HttpStatus.OK);
    }

    @PostMapping("/create-guest")
    public ResponseEntity<GlobalResponse<String >> createGuest (@Valid @RequestBody GuestDto.CreateGuest createGuest) {
        guestService.createGuest(createGuest);
        return new ResponseEntity<>(new GlobalResponse<>("Guest created successful!"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-guest/{guestId}")
    public ResponseEntity<GlobalResponse<String>> deleteGuest (@PathVariable UUID guestId) {
        guestService.deleteGuest(guestId);
        return new ResponseEntity<>(new GlobalResponse<>("Guest deleted successfully!"), HttpStatus.OK);
    }
}
