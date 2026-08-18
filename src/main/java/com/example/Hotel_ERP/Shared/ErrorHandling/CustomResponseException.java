package com.example.Hotel_ERP.Shared.ErrorHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponseException extends RuntimeException {

    private String message;
    private int code;

    public static CustomResponseException idIsNotFound(UUID id) {
        return new CustomResponseException("Error: ID (" + id + ") is not found!", 404);
    }

    public static CustomResponseException publicError (String message, int code) {
        return new CustomResponseException(message, code);
    }

    public static CustomResponseException unExpectedErrorOccurred () {
        return new CustomResponseException("An unexpected error occurred. please try again later", 500);
    }

    public static CustomResponseException badCredentials () {
        return new CustomResponseException("Bad credentials!", 403);
    }

    public static CustomResponseException duplicateItem (String item) {
        return new CustomResponseException("This " + item + " already exists", 400);
    }

    public static CustomResponseException roomIsNotAvailable () {
        return new CustomResponseException("This room is not available", 400);
    }

    public static CustomResponseException capacityExceeded () {
        return new CustomResponseException("Room capacity exceeded", 400);
    }

    public static CustomResponseException reservedRoom () {
        return new CustomResponseException("Room is already reserved for the selected dates", 400);
    }

    public static CustomResponseException BadCredentials (){
        return new CustomResponseException("Error: Bad credentials!", 401);
    }

    public static CustomResponseException PaymentAmountMustBeGreaterThanZero () {
        return new CustomResponseException("Error: Payment amount must be greater than zero!", 401);
    }

    public static CustomResponseException AmountEnteredInsufficient (BigDecimal amount) {
        return new CustomResponseException("Error: The amount entered is insufficient. The required amount is " + amount + " $", 401);
    }
}
