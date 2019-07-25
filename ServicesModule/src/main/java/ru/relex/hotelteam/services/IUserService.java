package ru.relex.hotelteam.services;

import java.util.List;
import ru.relex.hotelteam.dto.UserBaseDto;
import ru.relex.hotelteam.dto.UserDto;
import ru.relex.hotelteam.dto.UserSecurityDto;
import ru.relex.hotelteam.dto.UserUpdateDto;

public interface IUserService {

  UserBaseDto createUser(UserDto user);

  UserBaseDto findById(int id);

  List<UserBaseDto> listUsers();

  void delete(int id);

  void update(int id, UserUpdateDto updatedUser);

  void updateSecurityInfo(int id, UserSecurityDto updatedSecurityInfo);
}
