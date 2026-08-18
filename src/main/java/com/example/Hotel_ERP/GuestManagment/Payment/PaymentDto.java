package com.example.Hotel_ERP.GuestManagment.Payment;

import com.example.Hotel_ERP.GuestManagment.PaymentMethod.PaymentMethod;
import com.example.Hotel_ERP.GuestManagment.PaymentStatus.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentDto {

    public record CreatePayment (
            UUID reservationId,
            BigDecimal amount,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus
    ) { }

    public record UpdatePayment (
            UUID paymentId,
            BigDecimal amount,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus
    ) {}
}
