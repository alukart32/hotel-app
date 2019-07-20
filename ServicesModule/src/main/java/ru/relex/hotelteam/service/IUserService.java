package ru.relex.hotelteam.service;

import ru.relex.hotelteam.service.dto.UserBaseDto;
import ru.relex.hotelteam.service.dto.UserSecurityDto;
import ru.relex.hotelteam.service.dto.UserDto;
import ru.relex.hotelteam.service.dto.UserUpdateDto;

import java.util.List;

public interface IUserService {

  UserBaseDto createUser(UserDto user);

  UserBaseDto findById(int id);

  List<UserBaseDto> listUsers();

  void delete(int id);

  void update(int id, UserUpdateDto updatedUser);

  void updateSecurityInfo(int id, UserSecurityDto updatedSecurityInfo);
}
