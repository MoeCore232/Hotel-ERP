package com.example.Hotel_ERP.Booking.Reservation;

import com.example.Hotel_ERP.Accommodation.Room.Room;
import com.example.Hotel_ERP.Booking.ReservationStatus.ReservationStatus;
import com.example.Hotel_ERP.GuestManagment.Guest.Guest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "guest", nullable = false)
    private Guest guest;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "room", nullable = false)
    private Room room;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "number_of_guest", nullable = false)
    private int numberOfGuest;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "reservation_status", nullable = false)
    private ReservationStatus reservationStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Reservation createReservation (ReservationDto.CreateReservation createReservation, Guest guest, Room room) {
        Reservation reservation = new Reservation();
        reservation.guest = guest;
        reservation.room = room;
        reservation.checkInDate = createReservation.checkInDate();
        reservation.checkOutDate = createReservation.checkOutDate();
        reservation.numberOfGuest = createReservation.numberOfGuest();
        reservation.totalPrice = createReservation.totalPrice();
        reservation.reservationStatus = ReservationStatus.CONFIRMED;
        return reservation;
    }

    public void checkIn () {
        this.reservationStatus = ReservationStatus.CHECK_IN;
    }

    public void checkOut () {
        this.reservationStatus = ReservationStatus.CHECK_OUT;
    }

    public void cancel () {
        this.reservationStatus = ReservationStatus.CANCELED;
    }

}
