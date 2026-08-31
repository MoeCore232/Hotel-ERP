package com.example.Hotel_ERP.GuestManagment.Payment;

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
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/get-all-payments")
    public ResponseEntity<GlobalResponse<List<Payment>>> getAllPayment () {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(new GlobalResponse<>(payments), HttpStatus.OK);
    }

    @GetMapping("/get-payment-by-id/{paymentId}")
    public ResponseEntity<GlobalResponse<Payment>> getPaymentById (@PathVariable UUID paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return new ResponseEntity<>(new GlobalResponse<>(payment), HttpStatus.OK);
    }

    @PostMapping("/create-payment")
    public ResponseEntity<GlobalResponse<String>> createPayment (@Valid @RequestBody PaymentDto.CreatePayment createPayment) {
        paymentService.createPayment(createPayment);
        return new ResponseEntity<>(new GlobalResponse<>("Payment created successfully!"), HttpStatus.OK);
    }

    @PutMapping("/update-payment")
    public ResponseEntity<GlobalResponse<String>> updatePayment (@Valid @RequestBody PaymentDto.UpdatePayment updatePayment) {
        paymentService.updatePayment(updatePayment);
        return new ResponseEntity<>(new GlobalResponse<>("Payment updated successfully!"), HttpStatus.OK);
    }
}
