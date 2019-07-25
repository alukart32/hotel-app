package ru.relex.hotelteam.impl;

import java.util.List;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.dto.UserBaseDto;
import ru.relex.hotelteam.dto.UserDto;
import ru.relex.hotelteam.dto.UserSecurityDto;
import ru.relex.hotelteam.dto.UserUpdateDto;
import ru.relex.hotelteam.mapstruct.IUserMapstruct;
import ru.relex.hotelteam.IUserService;

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
  public UserBaseDto createUser(final UserDto user) {
    return mapstruct.toBaseDto(mapper.createUser(mapstruct.fromDto(user)));
  }

  @Override
  public UserBaseDto findById(final int id) {
    return mapstruct.toBaseDto(mapper.getUserById(id).orElseThrow());
  }

  @Override
  public List<UserBaseDto> listUsers() {
    return mapstruct.toDto(mapper.listUsers());
  }

  @Override
  public void delete(final int id) {
    mapper.deleteUser(id);
  }

  @Override
  public void update(int id, UserUpdateDto updatedUser) {

    User user = mapper.getUserById(id).
        orElseThrow(notFound("No user [ id = " + id + " ] was found!"));

    user.setFirstName(updatedUser.getFirstName());
    user.setLastName(updatedUser.getLastName());
    user.setMiddleName(updatedUser.getMiddleName());
    user.setBirthDate(updatedUser.getBirthDate());

    mapper.updateUser(user);
  }

  @Override
  public void updateSecurityInfo(int id, UserSecurityDto updatedSecurity) {
    User user = mapper.getUserById(id).
        orElseThrow(notFound("No user [ id = " + id + " ] was found!"));

    user.setLogin(updatedSecurity.getLogin());
    user.setEmail(updatedSecurity.getEmail());
    user.setPassword(updatedSecurity.getPassword());

    mapper.updateUserSecurityInfo(user);
  }

  private Supplier<RuntimeException> notFound(String s) {
    return () -> new RuntimeException(s);
  }
}

