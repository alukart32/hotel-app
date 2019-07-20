package ru.relex.hotelteam.service.dto;

import java.time.OffsetDateTime;

public class BookingCreateDto {

  private int userId;
  private int roomId;
  private OffsetDateTime checkInDate;
  private OffsetDateTime checkOutDate;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
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
