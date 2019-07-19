package ru.relex.hotelteam.service.impl;

import java.util.List;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.service.IUserService;
import ru.relex.hotelteam.service.dto.UserDto;
import ru.relex.hotelteam.service.dto.UserSecurityDto;
import ru.relex.hotelteam.service.dto.UserUpdateDto;
import ru.relex.hotelteam.service.mapstruct.IUserMapstruct;

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
  public UserDto createUser(final UserDto user) {
    return mapstruct.toDTO(mapper.createUser(mapstruct.fromDTO(user)));
  }

  @Override
  public UserDto findById(final int id) {
    return mapstruct.toDTO(mapper.getUserById(id).orElseThrow());
  }

  @Override
  public List<UserDto> listUsers() {
    return mapstruct.toDTO(mapper.listUsers());
  }

  @Override
  public void delete(final int id) {
    mapper.deleteUser(id);
  }

  @Override
  public void update(int id, UserUpdateDto updatedUser) {

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

  @Override
  public void updateSecurityInfo(int id, UserSecurityDto updatedAuth) {
    User user = mapper.getUserById(id).
        orElseThrow(notFound("No user [ id = " + id + " ] was found!"));

    user.setLogin(updatedAuth.getLogin());
    user.setEmail(updatedAuth.getEmail());
    user.setPassword(updatedAuth.getPassword());

    mapper.updateUserSecurityInfo(user);
  }

  private Supplier<RuntimeException> notFound(String s) {
    return () -> new RuntimeException(s);
  }
}

