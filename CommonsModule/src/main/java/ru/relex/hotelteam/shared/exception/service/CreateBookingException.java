package ru.relex.hotelteam.shared.exception.service;

/**
 * Created by Tarasov Ivan on 22/07/2019.
 */
public class CreateBookingException extends ServiceException {

  public CreateBookingException() {
    super();
  }

  public CreateBookingException(String message) {
    super(message);
  }
}
