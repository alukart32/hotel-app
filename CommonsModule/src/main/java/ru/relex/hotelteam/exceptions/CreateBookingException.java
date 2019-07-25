package ru.relex.hotelteam.exceptions;

/**
 * Created by Tarasov Ivan on 22/07/2019.
 */
public class CreateBookingException extends RuntimeException {

  public CreateBookingException() {
    super();
  }

  public CreateBookingException(String message) {
    super(message);
  }
}
