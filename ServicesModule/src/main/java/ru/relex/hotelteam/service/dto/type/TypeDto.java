package ru.relex.hotelteam.service.dto.type;

import java.math.BigDecimal;

public class TypeDto {

  private int id;
  private boolean twinbed;
  private int places;
  private BigDecimal price;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean getTwinbed() {
    return twinbed;
  }

  public void setTwinbed(boolean twinbed) {
    this.twinbed = twinbed;
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
