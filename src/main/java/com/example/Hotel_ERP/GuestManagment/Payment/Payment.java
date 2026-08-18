package com.example.Hotel_ERP.GuestManagment.Payment;

import com.example.Hotel_ERP.Booking.Reservation.Reservation;
import com.example.Hotel_ERP.GuestManagment.PaymentMethod.PaymentMethod;
import com.example.Hotel_ERP.GuestManagment.PaymentStatus.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation", nullable = false, unique = true)
    private Reservation reservation;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Payment createPayment (PaymentDto.CreatePayment createPayment, Reservation reservation) {
        Payment payment = new Payment();
        payment.reservation = reservation;
        payment.amount = createPayment.amount();
        payment.paymentMethod = createPayment.paymentMethod();
        payment.paymentStatus = createPayment.paymentStatus();
        return payment;
    }

    public static Payment updatePayment (Payment payment, PaymentDto.UpdatePayment updatePayment) {
        payment.amount = updatePayment.amount();
        payment.paymentMethod = updatePayment.paymentMethod();
        payment.paymentStatus = updatePayment.paymentStatus();
        return payment;
    }
}
