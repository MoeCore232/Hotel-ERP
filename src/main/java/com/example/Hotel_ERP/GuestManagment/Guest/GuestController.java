package com.example.Hotel_ERP.GuestManagment.Guest;

import com.example.Hotel_ERP.Shared.ErrorHandling.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @PostMapping("/create-guest")
    public ResponseEntity<GlobalResponse<String >> createGuest (@RequestBody GuestDto.CreateGuest createGuest) {
        guestService.createGuest(createGuest);
        return new ResponseEntity<>(new GlobalResponse<>("Guest created successful!"), HttpStatus.OK);
    }
}
