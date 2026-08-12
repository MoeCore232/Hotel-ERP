package com.example.Hotel_ERP.Booking.Reservation;

import com.example.Hotel_ERP.Accommodation.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, UUID> {
    boolean existsByRoomAndCheckInDateLessThanAndCheckOutDateGreaterThan (Room room, LocalDate checkOut, LocalDate checkIn);
}
