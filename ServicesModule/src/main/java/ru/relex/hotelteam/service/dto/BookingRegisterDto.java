package ru.relex.hotelteam.service.dto;

import java.time.LocalDateTime;

/**
 * Created by Tarasov Ivan on 21/07/2019 Time: 20:28
 */
public class BookingRegisterDto {

  private int userId;
  private LocalDateTime checkIn;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LocalDateTime getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDateTime checkIn) {
    this.checkIn = checkIn;
  }
}
