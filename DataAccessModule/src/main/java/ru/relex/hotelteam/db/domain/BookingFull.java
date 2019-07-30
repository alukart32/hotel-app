package ru.relex.hotelteam.db.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tarasov Ivan.
 */
public class BookingFull {

  // room
  private int roomId;
  private int number;
  private int floor;

  private int categoryId;
  private String categoryName;

  private int typeId;
  private int places;
  private Boolean twinBed;

  private List<Facility> facilities = new ArrayList<>(0);
  private BigDecimal dailyPrice;

  // booking
  private int bookingId;
  private int userId;
  private LocalDateTime checkInDate;
  private LocalDateTime checkOutDate;
  private LocalDateTime realCheckInDate;
  private LocalDateTime realCheckOutDate;

  // payment
  private boolean paid;
  private LocalDateTime date;
  private BigDecimal total;

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
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

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public int getPlaces() {
    return places;
  }

  public void setPlaces(int places) {
    this.places = places;
  }

  public Boolean getTwinBed() {
    return twinBed;
  }

  public void setTwinBed(Boolean twinBed) {
    this.twinBed = twinBed;
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

  public int getBookingId() {
    return bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LocalDateTime getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(LocalDateTime checkInDate) {
    this.checkInDate = checkInDate;
  }

  public LocalDateTime getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(LocalDateTime checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  public LocalDateTime getRealCheckInDate() {
    return realCheckInDate;
  }

  public void setRealCheckInDate(LocalDateTime realCheckInDate) {
    this.realCheckInDate = realCheckInDate;
  }

  public LocalDateTime getRealCheckOutDate() {
    return realCheckOutDate;
  }

  public void setRealCheckOutDate(LocalDateTime realCheckOutDate) {
    this.realCheckOutDate = realCheckOutDate;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}
