package ru.relex.hotelteam.service.dto;

import java.time.OffsetDateTime;

public class BookingUpdateDto {

  private OffsetDateTime checkInDate;
  private OffsetDateTime checkOutDate;
  private int roomId;

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

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
}
