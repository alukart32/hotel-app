package ru.relex.hotelteam.dto.bookings;

import java.time.LocalDateTime;

/**
 * Created by Tarasov Ivan on 26/07/2019 Time: 8:01.
 */
public class BookingIntervalDto {

  private LocalDateTime checkInDate;
  private LocalDateTime checkOutDate;

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
}
