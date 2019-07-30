package ru.relex.hotelteam.dto.bookings;

import java.util.Date;

/**
 * Created by Tarasov Ivan on 26/07/2019 Time: 8:01.
 */
public class BookingIntervalDto {

  private Date checkInDate;
  private Date checkOutDate;


  public Date getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(Date checkInDate) {
    this.checkInDate = checkInDate;
  }

  public Date getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(Date checkOutDate) {
    this.checkOutDate = checkOutDate;
  }
}
