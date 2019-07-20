package ru.relex.hotelteam.db.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Created by Tarasov Ivan on 20/07/2019 Time: 19:01
 */
public class BookingPayment {

  private int id;
  private int userId;
  private int bookingId;
  private OffsetDateTime date;
  private BigDecimal total;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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
