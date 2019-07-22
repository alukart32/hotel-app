package ru.relex.hotelteam.shared.exception.service;

/**
 * Created by Tarasov Ivan on 22/07/2019 Time: 11:33.
 */
public class ServiceException extends Exception {

  public ServiceException() {
    super();
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }
}
