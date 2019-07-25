package ru.relex.hotelteam.exceptions;

/**
 * Created by Tarasov Ivan on 22/07/2019.
 */
public class RegisterGuestException extends RuntimeException {

  public RegisterGuestException() {
    super();
  }

  public RegisterGuestException(String message) {
    super(message);
  }
}
