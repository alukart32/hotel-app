package ru.relex.hotelteam.service.dto;

import java.time.OffsetDateTime;

public class BookingCreateDto {

  private int userId;

  private OffsetDateTime checkInDate;

  private OffsetDateTime checkOutDate;
  // private Room room;


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
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
