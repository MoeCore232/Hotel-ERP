package com.example.Hotel_ERP.GuestManagment.Payment;

import com.example.Hotel_ERP.Booking.Reservation.Reservation;
import com.example.Hotel_ERP.Booking.Reservation.ReservationRepo;
import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private ReservationRepo reservationRepo;

    public List<Payment> getAllPayments () {
        List<Payment> payments = paymentRepo.findAll();
        return payments;
    }

    public Payment getPaymentById (UUID paymentId) {
        Payment findPayment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(paymentId));
        return findPayment;
    }

    public void createPayment (PaymentDto.CreatePayment createPayment) {
        Reservation findReservation = reservationRepo.findById(createPayment.reservationId())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(createPayment.reservationId()));

        BigDecimal reservationAmount = findReservation.getTotalPrice();
        BigDecimal paymentAmount = createPayment.amount();

        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw CustomResponseException.PaymentAmountMustBeGreaterThanZero();
        }

        if (paymentAmount.compareTo(reservationAmount) < 0) {
            throw CustomResponseException.AmountEnteredInsufficient(findReservation.getTotalPrice());
        }

        Payment payment = Payment.createPayment(createPayment, findReservation);
        paymentRepo.save(payment);
    }

    public void updatePayment (PaymentDto.UpdatePayment updatePayment) {
        Payment findPayment = paymentRepo.findById(updatePayment.paymentId())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(updatePayment.paymentId()));
        Payment payment = Payment.updatePayment(findPayment, updatePayment);
        paymentRepo.save(payment);
    }
}
