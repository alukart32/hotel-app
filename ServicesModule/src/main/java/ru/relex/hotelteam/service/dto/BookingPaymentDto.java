package ru.relex.hotelteam.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Tarasov Ivan on 20/07/2019
 */
public class BookingPaymentDto {

  private int userId;
  private int bookingId;
  private int roomId;
  private int amountOfReservedDays;
  private LocalDateTime timePayment;
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

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public int getAmountOfReservedDays() {
    return amountOfReservedDays;
  }

  public void setAmountOfReservedDays(int amountOfReservedDays) {
    this.amountOfReservedDays = amountOfReservedDays;
  }

  public LocalDateTime getTimePayment() {
    return timePayment;
  }

  public void setTimePayment(LocalDateTime timePayment) {
    this.timePayment = timePayment;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}
