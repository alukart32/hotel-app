package ru.relex.hotelteam.service;

import java.util.List;
import ru.relex.hotelteam.service.dto.UserDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

public interface IUserService {

  UserDTO createUser(UserDTO user);

  UserDTO findById(int id);

  List<UserDTO> listUsers();

  void delete(int id);

  void update(int id, UserUpdateDTO updatedUser);
}
