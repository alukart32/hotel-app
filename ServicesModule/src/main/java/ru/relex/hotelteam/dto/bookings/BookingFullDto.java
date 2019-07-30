package ru.relex.hotelteam.dto.bookings;

import ru.relex.hotelteam.dto.RoomWithIdDto;

/**
 * Created by Tarasov Ivan.
 */
public class BookingFullDto {

  private BookingDto booking;
  private RoomWithIdDto room;
  private BookingPaymentDto payment;

  public BookingDto getBooking() {
    return booking;
  }

  public void setBooking(BookingDto booking) {
    this.booking = booking;
  }

  public RoomWithIdDto getRoom() {
    return room;
  }

  public void setRoom(RoomWithIdDto room) {
    this.room = room;
  }

  public BookingPaymentDto getPayment() {
    return payment;
  }

  public void setPayment(BookingPaymentDto payment) {
    this.payment = payment;
  }
}
