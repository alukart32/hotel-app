package ru.relex.hotelteam.shared.exception.service;

public class CreateBookingException extends ServiceException {

  public CreateBookingException() {
    super();
  }

  public CreateBookingException(String message) {
    super(message);
  }
}
