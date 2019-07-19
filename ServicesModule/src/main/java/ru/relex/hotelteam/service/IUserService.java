package ru.relex.hotelteam.service;

import ru.relex.hotelteam.service.dto.UserSecurityDto;
import ru.relex.hotelteam.service.dto.UserDto;
import ru.relex.hotelteam.service.dto.UserUpdateDto;

import java.util.List;

public interface IUserService {

  UserDto createUser(UserDto user);

  UserDto findById(int id);

  List<UserDto> listUsers();

  void delete(int id);

  void update(int id, UserUpdateDto updatedUser);

  void updateAuth(int id, UserSecurityDto updatedAuth);
}
