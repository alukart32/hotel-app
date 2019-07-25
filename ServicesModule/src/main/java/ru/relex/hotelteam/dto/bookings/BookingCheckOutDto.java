package ru.relex.hotelteam.dto.bookings;

import java.time.LocalDateTime;

/**
 * Created by Tarasov Ivan on 20/07/2019.
 */
public class BookingCheckOutDto {

  private int userId;
  private int bookingId;
  private LocalDateTime checkOutDate;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getBookingId() {
    return bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public LocalDateTime getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(LocalDateTime checkOutDate) {
    this.checkOutDate = checkOutDate;
  }
}
