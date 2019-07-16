package ru.relex.hotelteam.db.domain;

import java.math.BigDecimal;

public class RoomType {

    private int id;
    private boolean twinBed;
    private int places;
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTwinBed() {
        return twinBed;
    }

    public void setTwinBed(boolean twinBed) {
        this.twinBed = twinBed;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
