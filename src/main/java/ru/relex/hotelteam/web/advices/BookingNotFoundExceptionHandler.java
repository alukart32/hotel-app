package ru.relex.hotelteam.web.advices;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.relex.hotelteam.shared.exception.service.BookingNotFoundException;

/**
 * Created by Tarasov Ivan on 22/07/2019 Time: 17:36.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BookingNotFoundExceptionHandler extends
    ResponseEntityExceptionHandler {

  @ExceptionHandler(BookingNotFoundException.class)
  protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Reservation wasn't found";
    return handleExceptionInternal(ex, bodyOfResponse,
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}
