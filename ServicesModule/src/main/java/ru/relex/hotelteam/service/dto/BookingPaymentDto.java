package ru.relex.hotelteam.service.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BookingPaymentDto {

  private int userId;
  private int bookingId;
  private int amountOfReservedDays;
  private OffsetDateTime timePayment;
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

  public int getAmountOfReservedDays() {
    return amountOfReservedDays;
  }

  public void setAmountOfReservedDays(int amountOfReservedDays) {
    this.amountOfReservedDays = amountOfReservedDays;
  }

  public OffsetDateTime getTimePayment() {
    return timePayment;
  }

  public void setTimePayment(OffsetDateTime timePayment) {
    this.timePayment = timePayment;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}
