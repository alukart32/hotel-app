package ru.relex.hotelteam.service;

import ru.relex.hotelteam.service.dto.UserDTO;

import java.util.List;

public interface IUserService {

    UserDTO createUser(UserDTO user);

    UserDTO findById(int id);

    List<UserDTO> listUsers();

    void delete(int id);

    UserDTO update(int id, UserDTO updatedUser);
}