package ru.relex.hotelteam.dto.bookings;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Tarasov Ivan on 20/07/2019.
 */
public class BookingPaymentDto {

  // ненадобностью удалить
  private int userId;

  private int bookingId;
  private int roomId;
  private int amountOfDays;
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

  public int getAmountOfDays() {
    return amountOfDays;
  }

  public void setAmountOfDays(int amountOfDays) {
    this.amountOfDays = amountOfDays;
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
