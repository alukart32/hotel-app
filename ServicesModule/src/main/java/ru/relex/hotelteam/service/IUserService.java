package ru.relex.hotelteam.service;

import ru.relex.hotelteam.service.dto.domain.UserDTO;
import ru.relex.hotelteam.service.dto.domain.UserUpdateDTO;

import java.util.List;

public interface IUserService {

    UserDTO createUser(UserDTO user);

    UserDTO findById(int id);

    List<UserDTO> listUsers();

    void delete(int id);

    void update(int id, UserUpdateDTO updatedUser);
}