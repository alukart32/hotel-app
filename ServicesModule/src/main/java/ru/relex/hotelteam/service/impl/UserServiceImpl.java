package ru.relex.hotelteam.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.service.IUserService;
import ru.relex.hotelteam.service.dto.domain.UserDTO;
import ru.relex.hotelteam.service.dto.domain.UserUpdateDTO;
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
        return mapstruct.toDTO(mapper.getById(id).orElseThrow());
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
    public UserUpdateDTO update(int id, UserUpdateDTO updatedUser) {

        User user = mapper.getById(id).orElseThrow();

        /**
         * просто без проверок
         */
         user.setId(id);
         user.setAuthority(user.getAuthority());
         user.setPassword(user.getPassword());
         user.setFirstName(updatedUser.getFirstName());
         user.setLastName(updatedUser.getLastName());
         user.setMiddleName(updatedUser.getMiddleName());
         user.setEmail(updatedUser.getEmail());

         mapper.updateUser(user);
         // то ли тот сохранённый объект возвращаем, то ли изменённый объект на сохранение, чтобы
         //  чтобы не обращаться лишний раз к бд ?
         return mapstruct.toUserUpdateDTO(user);
    }
}

