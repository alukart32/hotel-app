package ru.relex.hotelteam.service.dto;

import java.time.LocalDateTime;

public class BookingUpdateDto {

  private int roomId;
  private int userId;
  private LocalDateTime checkInDate;
  private LocalDateTime checkOutDate;

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

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
}
