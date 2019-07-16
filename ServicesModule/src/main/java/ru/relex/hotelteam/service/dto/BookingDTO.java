package ru.relex.hotelteam.service.dto;

import java.time.OffsetDateTime;

public class BookingDTO {

    private int id;
    private int userId;
    // private Room room;

    private OffsetDateTime checkInDate;

    private OffsetDateTime checkOutDate;

    private OffsetDateTime realCheckInDate;
    private OffsetDateTime realCheckOutDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public OffsetDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(OffsetDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public OffsetDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(OffsetDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public OffsetDateTime getRealCheckInDate() {
        return realCheckInDate;
    }

    public void setRealCheckInDate(OffsetDateTime realCheckInDate) {
        this.realCheckInDate = realCheckInDate;
    }

    public OffsetDateTime getRealCheckOutDate() {
        return realCheckOutDate;
    }

    public void setRealCheckOutDate(OffsetDateTime realCheckOutDate) {
        this.realCheckOutDate = realCheckOutDate;
    }
}
