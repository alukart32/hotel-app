package ru.relex.hotelteam.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.service.IUserService;
import ru.relex.hotelteam.service.dto.UserDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;
import ru.relex.hotelteam.service.mapstruct.IUserMapstruct;

import java.util.List;
import java.util.function.Supplier;

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
        return mapstruct.toSafeDTO(mapper.createUser(mapstruct.fromSafeDTO(user)));
    }

    @Override
    public UserDTO findById(final int id) {
        return mapstruct.toSafeDTO(mapper.getUserById(id).orElseThrow());
    }

    @Override
    public List<UserDTO> listUsers() {
        return mapstruct.toSafeDTOs(mapper.listUsers());
    }
    @Override
    public void delete(final int id) {
        mapper.deleteUser(id);
    }

    @Override
    public void update(int id, UserUpdateDTO updatedUser) {

        User user = mapper.getUserById(id).
                orElseThrow(notFound("No user [ id = " + id + " ] was found!"));

         user.setId(id);
         user.setAuthority(user.getAuthority());
         user.setPassword(user.getPassword());
         user.setFirstName(updatedUser.getFirstName());
         user.setLastName(updatedUser.getLastName());
         user.setMiddleName(updatedUser.getMiddleName());
         user.setBirthDate(updatedUser.getBirthDate());

         mapper.updateUser(user);
    }

    private Supplier<RuntimeException> notFound(String s) {
        return () -> new RuntimeException(s);
    }
}

