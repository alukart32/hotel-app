package ru.relex.hotelteam.service.dto;

import java.math.BigDecimal;
import java.util.List;
import ru.relex.hotelteam.db.domain.Facility;

/**
 * Author: Yakimov Date: 20.07.2019 Time: 21:04
 */
public class RoomBaseDto {

  private int number;
  private int floor;

  private BigDecimal dailyPrice;


  private List<Facility> facilities;

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public List<Facility> getFacilities() {
    return facilities;
  }

  public void setFacilities(List<Facility> facilities) {
    this.facilities = facilities;
  }


  public BigDecimal getDailyPrice() {
    return dailyPrice;
  }

  public void setDailyPrice(BigDecimal dailyPrice) {
    this.dailyPrice = dailyPrice;
  }

}
