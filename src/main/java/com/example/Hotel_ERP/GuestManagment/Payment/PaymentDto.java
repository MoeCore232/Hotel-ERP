package com.example.Hotel_ERP.GuestManagment.Payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentDto {

    public record CreatePayment (
            UUID reservationId,
            @Positive(message = "is required!")
            BigDecimal amount,
            @NotNull(message = "is required!")
            PaymentMethod paymentMethod,
            @NotNull(message = "is required!")
            PaymentStatus paymentStatus
    ) { }

    public record UpdatePayment (
            UUID paymentId,
            @Positive(message = "is required!")
            BigDecimal amount,
            @NotNull(message = "is required!")
            PaymentMethod paymentMethod,
            @NotNull(message = "is required!")
            PaymentStatus paymentStatus
    ) {}
}
