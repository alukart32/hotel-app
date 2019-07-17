package ru.relex.hotelteam.service;

import java.util.List;
import ru.relex.hotelteam.service.dto.UserDto;
import ru.relex.hotelteam.service.dto.UserUpdateDto;

public interface IUserService {

  UserDto createUser(UserDto user);

  UserDto findById(int id);

  List<UserDto> listUsers();

  void delete(int id);

  void update(int id, UserUpdateDto updatedUser);
}
