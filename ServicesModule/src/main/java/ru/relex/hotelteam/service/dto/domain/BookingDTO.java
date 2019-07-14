package ru.relex.hotelteam.service.dto.domain;

import ru.relex.hotelteam.db.domain.User;

import java.time.OffsetDateTime;

public class BookingDTO {

    private User user;
    // private Room room;

    private OffsetDateTime checkInDate;

    private OffsetDateTime checkOutDate;

    private OffsetDateTime realCheckInDate;
    private OffsetDateTime realCheckOutDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
