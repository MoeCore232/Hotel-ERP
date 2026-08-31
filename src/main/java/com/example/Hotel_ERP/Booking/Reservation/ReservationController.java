package com.example.Hotel_ERP.Booking.Reservation;

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
@RequestMapping("/api/reservation")
public class ReservationController {

   @Autowired
   private ReservationService reservationService;

   @GetMapping("/get-all-reservations")
   public ResponseEntity<GlobalResponse<List<Reservation>>> getAllReservations () {
       List<Reservation> reservations = reservationService.getAllReservation();
       return new ResponseEntity<>(new GlobalResponse<>(reservations), HttpStatus.OK);
   }

   @GetMapping("/get-reservation-by-id/{reservationId}")
   public ResponseEntity<GlobalResponse<Reservation>> getReservationById (@PathVariable UUID reservationId) {
       Reservation reservation = reservationService.getReservationById(reservationId);
       return new ResponseEntity<>(new GlobalResponse<>(reservation), HttpStatus.OK);
   }

   @PostMapping("/create-reservation")
    public ResponseEntity<GlobalResponse<String>> createReservation (@Valid @RequestBody ReservationDto.CreateReservation createReservation) {
       reservationService.createReservation(createReservation);
       return new ResponseEntity<>(new GlobalResponse<>("Reservation created successful!"), HttpStatus.OK);
   }

   @PutMapping("/update-reservation")
    public ResponseEntity<GlobalResponse<String>> updateReservation (@Valid @RequestBody ReservationDto.UpdateReservation updateReservation) {
       reservationService.updateReservation(updateReservation);
       return new ResponseEntity<>(new GlobalResponse<>("Reservation updated successfully"), HttpStatus.OK);
   }
}
