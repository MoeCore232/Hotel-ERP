package com.example.Hotel_ERP.Accommodation.RoomType;

public enum RoomType {
    SINGLE,
    DOUBLE,
    TWIN,
    FAMILY,
    SUITE;
    public static boolean isValid (String value) {
        for (RoomType roomType : values()) {
            if (roomType.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
