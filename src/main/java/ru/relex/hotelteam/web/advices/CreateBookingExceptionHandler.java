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
import ru.relex.hotelteam.shared.exception.service.CreateBookingException;

/**
 * Created by Tarasov Ivan on 22/07/2019.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CreateBookingExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = CreateBookingException.class
  )
  protected ResponseEntity<Object> handleCreateBookingException(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Chosen room is booked on the dates";
    return handleExceptionInternal(ex, bodyOfResponse,
        new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
  }
}
