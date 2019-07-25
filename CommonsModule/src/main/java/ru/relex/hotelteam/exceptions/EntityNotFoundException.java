package ru.relex.hotelteam.exceptions;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String entity) {
    super(String.format("%s not found", entity));
  }

  public EntityNotFoundException(String entity, int id) {
    super(String.format("%s with id '%d' not found", entity, id));
  }

}
