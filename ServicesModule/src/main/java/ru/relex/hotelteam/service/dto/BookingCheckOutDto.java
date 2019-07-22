package ru.relex.hotelteam.service.dto;

import java.time.LocalDateTime;

/**
 * Created by Tarasov Ivan on 20/07/2019
 */
public class BookingCheckOutDto {

  private int userId;
  private LocalDateTime checkOutDate;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LocalDateTime getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(LocalDateTime checkOutDate) {
    this.checkOutDate = checkOutDate;
  }
}
