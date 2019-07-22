package ru.relex.hotelteam.shared.exception.service;

/**
 * Created by Tarasov Ivan on 22/07/2019 Time: 17:35
 */
public class BookingNotFoundException extends ServiceException {

  public BookingNotFoundException() {
    super();
  }

  public BookingNotFoundException(String message) {
    super(message);
  }
}
