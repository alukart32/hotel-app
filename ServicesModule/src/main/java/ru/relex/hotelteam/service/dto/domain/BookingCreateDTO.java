package ru.relex.hotelteam.service.dto.domain;

import ru.relex.hotelteam.db.domain.User;

import java.time.OffsetDateTime;

public class BookingCreateDTO {

    private User user;
    private OffsetDateTime checkInDate;
    private OffsetDateTime checkOutDate;
    // private Room room;

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
}
