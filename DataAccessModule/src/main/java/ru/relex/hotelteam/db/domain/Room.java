package ru.relex.hotelteam.db.domain;

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

  //private List<Facility> facilities;

  private Integer dailyPrice;

  public Room(Integer id, Integer number, Integer floor, Integer categoryId, String categoryName,
      Integer typeId, Integer places, Boolean twinBed, Integer dailyPrice) {
    this.id = id;
    this.number = number;
    this.floor = floor;
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.typeId = typeId;
    this.places = places;
    this.twinBed = twinBed;
    this.dailyPrice = dailyPrice;
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

  public Integer getDailyPrice() {
    return dailyPrice;
  }

  public void setDailyPrice(Integer dailyPrice) {
    this.dailyPrice = dailyPrice;
  }
}
