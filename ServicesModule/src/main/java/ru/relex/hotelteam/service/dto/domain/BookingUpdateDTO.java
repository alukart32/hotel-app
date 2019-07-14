package ru.relex.hotelteam.service.dto.domain;

import java.time.OffsetDateTime;

public class BookingUpdateDTO {

    private OffsetDateTime checkInDate;
    private OffsetDateTime checkOutDate;
    // private Room room;

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
}
