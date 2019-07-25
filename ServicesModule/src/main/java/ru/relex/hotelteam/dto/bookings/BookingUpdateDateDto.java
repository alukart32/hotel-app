package ru.relex.hotelteam.dto.bookings;

import java.time.LocalDateTime;

/**
 * Created by Tarasov Ivan on 23/07/2019 Time: 15:42.
 */
public class BookingUpdateDateDto {

  private LocalDateTime realCheckInDate;
  private LocalDateTime realCheckOutDate;

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
}
