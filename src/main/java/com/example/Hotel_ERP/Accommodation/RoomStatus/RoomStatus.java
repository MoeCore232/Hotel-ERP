package com.example.Hotel_ERP.Accommodation.RoomStatus;

public enum RoomStatus {
    AVAILABLE,
    OCCUPIED,
    MAINTENANCE;
    public static boolean isValid (String value) {
        for (RoomStatus roomStatus : values()) {
            if (roomStatus.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
