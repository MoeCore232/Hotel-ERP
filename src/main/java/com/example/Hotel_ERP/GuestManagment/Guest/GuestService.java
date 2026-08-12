package com.example.Hotel_ERP.GuestManagment.Guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepo guestRepo;

    public List<Guest> getAllGuests () {
        return guestRepo.findAll();
    }

    public void createGuest (GuestDto.CreateGuest createGuest) {
        Guest guest = Guest.createGuest(createGuest);
        guestRepo.save(guest);
    }
}
