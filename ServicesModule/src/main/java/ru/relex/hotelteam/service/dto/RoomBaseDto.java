package ru.relex.hotelteam.service.dto;

import java.util.List;
import ru.relex.hotelteam.db.domain.Facility;
import ru.relex.hotelteam.db.domain.RoomCategory;
import ru.relex.hotelteam.db.domain.RoomType;

/**
 * Author: Yakimov Date: 20.07.2019 Time: 21:04
 */
public class RoomBaseDto {

  private int id;
  private int number;
  private int floor;

  private RoomType type;
  private RoomCategory category;
  private List<Facility> facilities;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public RoomType getType() {
    return type;
  }

  public void setType(RoomType type) {
    this.type = type;
  }

  public RoomCategory getCategory() {
    return category;
  }

  public void setCategory(RoomCategory category) {
    this.category = category;
  }

  public List<Facility> getFacilities() {
    return facilities;
  }

  public void setFacilities(List<Facility> facilities) {
    this.facilities = facilities;
  }

}
