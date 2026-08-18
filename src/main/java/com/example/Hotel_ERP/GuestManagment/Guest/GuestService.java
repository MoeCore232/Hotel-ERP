package com.example.Hotel_ERP.GuestManagment.Guest;

import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Guest getGuestById (UUID guestId) {
        Guest findGuest = guestRepo.findById(guestId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(guestId));
        return findGuest;
    }

    public void deleteGuest (UUID guestId) {
        Guest findGuest = guestRepo.findById(guestId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(guestId));
        guestRepo.deleteById(findGuest.getId());
    }
}
