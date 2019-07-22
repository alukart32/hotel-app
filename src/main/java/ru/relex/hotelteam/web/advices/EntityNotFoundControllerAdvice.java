package ru.relex.hotelteam.web.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundControllerAdvice {

  @ExceptionHandler(value = EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }
}
