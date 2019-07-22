package ru.relex.hotelteam.shared.exception.service;

/**
 * Created by Tarasov Ivan on 20/07/2019 Time: 18:10.
 */
public class UserNotFoundException extends ServiceException {

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
