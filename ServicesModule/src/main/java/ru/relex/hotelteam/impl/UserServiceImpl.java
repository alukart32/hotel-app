package ru.relex.hotelteam.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.IUserService;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.dto.UserBaseDto;
import ru.relex.hotelteam.dto.UserDto;
import ru.relex.hotelteam.dto.UserSecurityDto;
import ru.relex.hotelteam.dto.UserUpdateDto;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;
import ru.relex.hotelteam.mapstruct.IUserMapstruct;

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
    User u = mapstruct.toDomain(user);
    return mapstruct.toBaseDto(mapper.createUser(u));
  }

  @Override
  public UserBaseDto findById(final int id) throws EntityNotFoundException {
    return mapstruct.toBaseDto(mapper.getUserById(id)
        .orElseThrow(() -> new EntityNotFoundException("User", id)));
  }

  @Override
  public List<UserBaseDto> listUsers() {
    return mapstruct.fromDomain(mapper.listUsers());
  }

  @Override
  public void delete(final int id) {
    mapper.deleteUser(id);
  }

  @Override
  public void update(int id, UserUpdateDto updatedUser) {
    User user = mapper.getUserById(id).
        orElseThrow(() -> new EntityNotFoundException("User", id));

    user.setFirstName(updatedUser.getFirstName());
    user.setLastName(updatedUser.getLastName());
    user.setMiddleName(updatedUser.getMiddleName());
    user.setBirthDate(updatedUser.getBirthDate());

    mapper.updateUser(user);
  }

  @Override
  public void updateSecurityInfo(int id, UserSecurityDto updatedSecurity) {
    User user = mapper.getUserById(id)
        .orElseThrow(() -> new EntityNotFoundException("User", id));

    user.setLogin(updatedSecurity.getLogin());
    user.setEmail(updatedSecurity.getEmail());
    user.setPassword(updatedSecurity.getPassword());

    mapper.updateUserSecurityInfo(user);
  }
}

