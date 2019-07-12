package ru.relex.hotelteam.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.service.IUserService;
import ru.relex.hotelteam.service.dto.UserDTO;
import ru.relex.hotelteam.service.mapstruct.IUserMapstruct;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserMapper mapper;
    private final IUserMapstruct mapstruct;

    public UserServiceImpl(final IUserMapper mapper,
                           final IUserMapstruct mapstruct) {
        this.mapper = mapper;
        this.mapstruct = mapstruct;
    }

    @Override
    public UserDTO createUser(final UserDTO user) {
        return mapstruct.toDTO(mapper.createUser(mapstruct.fromDTO(user)));
    }

    @Override
    public UserDTO findById(final int id) {
        return mapstruct.toDTO(mapper.getUserById(id));
    }

    @Override
    public List<UserDTO> listUsers() {
        return mapstruct.toDTO(mapper.listUsers());
    }

    @Override
    public void delete(final int id) {
        mapper.deleteUser(id);
    }

    @Override
    public UserDTO update(int id, UserDTO updatedUser) {
        return null;
    }
}

