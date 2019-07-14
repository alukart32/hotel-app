package ru.relex.hotelteam.service;

import ru.relex.hotelteam.service.dto.UserSafeDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

import java.util.List;

public interface IUserService {

    UserSafeDTO createUser(UserSafeDTO user);

    UserSafeDTO findById(int id);

    List<UserSafeDTO> listUsers();

    void delete(int id);

    UserUpdateDTO update(int id, UserUpdateDTO updatedUser);
}