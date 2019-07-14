package ru.relex.hotelteam.db.domain;

import java.time.OffsetDateTime;

public class Booking {

    private int id;

    // тот, кто бронирует
    private User user;

    //private Room room;

    // время заезда и выезда по бронированию
    private OffsetDateTime checkInDate;
    private OffsetDateTime checkOutDate;

    // фактическое время заезда и выезда
    private OffsetDateTime realCheckInDate;
    private OffsetDateTime realCheckOutDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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