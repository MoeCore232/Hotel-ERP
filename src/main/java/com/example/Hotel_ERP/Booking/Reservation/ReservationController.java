package com.example.Hotel_ERP.Booking.Reservation;

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
@RequestMapping("/api/reservation")
public class ReservationController {

   @Autowired
   private ReservationService reservationService;

   @GetMapping("/get-all-reservations")
   public ResponseEntity<GlobalResponse<List<Reservation>>> getAllReservations () {
       List<Reservation> reservations = reservationService.getAllReservation();
       return new ResponseEntity<>(new GlobalResponse<>(reservations), HttpStatus.OK);
   }

   @PostMapping("/create-reservation")
    public ResponseEntity<GlobalResponse<String>> createReservation (@RequestBody ReservationDto.CreateReservation createReservation) {
       reservationService.createReservation(createReservation);
       return new ResponseEntity<>(new GlobalResponse<>("Reservation created successful!"), HttpStatus.OK);
   }

}
