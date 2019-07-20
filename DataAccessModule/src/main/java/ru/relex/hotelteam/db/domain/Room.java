package ru.relex.hotelteam.db.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Author: Yakimov Date: 17.07.2019 Time: 22:51
 */
public class Room {

  private Integer id;
  private Integer number;
  private Integer floor;

  private Integer categoryId;
  private String categoryName;

  private Integer typeId;
  private Integer places;
  private Boolean twinBed;


  private List<Facility> facilities;
  private BigDecimal dailyPrice;

  public Room() {

  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getPlaces() {
    return places;
  }

  public void setPlaces(Integer places) {
    this.places = places;
  }

  public Boolean getTwinBed() {
    return twinBed;
  }

  public void setTwinBed(Boolean twinBed) {
    this.twinBed = twinBed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Room room = (Room) o;
    return getId().equals(room.getId());
  }

  @Override
  public String toString() {
    return "Room{" +
        "id=" + id +
        ", number=" + number +
        ", floor=" + floor +
        ", categoryId=" + categoryId +
        ", categoryName='" + categoryName + '\'' +
        ", typeId=" + typeId +
        ", places=" + places +
        ", twinBed=" + twinBed +
        ", facilities=" + facilities.toString() +
        ", dailyPrice=" + dailyPrice +
        '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
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
