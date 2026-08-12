package com.example.Hotel_ERP.Booking.Reservation;

import com.example.Hotel_ERP.Accommodation.Room.Room;
import com.example.Hotel_ERP.Accommodation.Room.RoomRepo;
import com.example.Hotel_ERP.Accommodation.RoomStatus.RoomStatus;
import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import com.example.Hotel_ERP.GuestManagment.Guest.Guest;
import com.example.Hotel_ERP.GuestManagment.Guest.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private GuestRepo guestRepo;

    @Autowired
    private RoomRepo roomRepo;

    public List<Reservation> getAllReservation () {
        return reservationRepo.findAll();
    }

    public void createReservation (ReservationDto.CreateReservation createReservation) {
        Guest findGuest = guestRepo.findById(createReservation.guestId())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(createReservation.guestId()));

        Room findRoom = roomRepo.findById(createReservation.roomId())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(createReservation.roomId()));

        if (findRoom.getRoomStatus() != RoomStatus.AVAILABLE) {
            throw CustomResponseException.roomIsNotAvailable();
        }

        if (findRoom.getCaption() < createReservation.numberOfGuest()) {
            throw CustomResponseException.capacityExceeded();
        }

        LocalDate checkOut = createReservation.checkOutDate();
        LocalDate checkIn = createReservation.checkInDate();

        boolean result = reservationRepo.existsByRoomAndCheckInDateLessThanAndCheckOutDateGreaterThan(findRoom, checkOut, checkIn);
        if (result) {
            throw CustomResponseException.reservedRoom();
        }

        Reservation reservation = Reservation.createReservation(createReservation, findGuest, findRoom);
        reservationRepo.save(reservation);
    }

    public void deleteReservation (UUID reservationId) {
        Reservation findReservation = reservationRepo.findById(reservationId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(reservationId));
        reservationRepo.deleteById(findReservation.getId());
    }

}
