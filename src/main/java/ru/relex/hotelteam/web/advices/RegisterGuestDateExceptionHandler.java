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
import ru.relex.hotelteam.exceptions.RegisterGuestException;

/**
 * Created by Tarasov Ivan on 22/07/2019.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RegisterGuestDateExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RegisterGuestException.class)
  protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Registration date is out of booking dates";
    return handleExceptionInternal(ex, bodyOfResponse,
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}
