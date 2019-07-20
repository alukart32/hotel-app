package ru.relex.hotelteam.service.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BookingPaymentDto {

  private int userId;
  private int bookingId;
  private OffsetDateTime date;
  private BigDecimal total;

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

  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}
