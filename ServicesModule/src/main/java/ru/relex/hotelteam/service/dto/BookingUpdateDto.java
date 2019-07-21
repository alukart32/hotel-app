package ru.relex.hotelteam.service.dto;

import java.time.LocalDateTime;

public class BookingUpdateDto {

  private int roomId;
  private int userId;
  private LocalDateTime checkInDate;
  private LocalDateTime checkOutDate;
  private LocalDateTime realCheckInDate;
  private LocalDateTime realCheckOutDate;

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

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
}
