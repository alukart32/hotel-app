package ru.relex.hotelteam.mapstruct;


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.dto.UserBaseDto;
import ru.relex.hotelteam.dto.UserDto;
import ru.relex.hotelteam.dto.UserSecurityDto;
import ru.relex.hotelteam.dto.UserUpdateDto;

@Mapper(componentModel = "spring")
public abstract class IUserMapstruct {

  private PasswordEncoder passwordEncoder;

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  String mapPassword(UserDto dto) {
    return passwordEncoder.encode(dto.getPassword());
  }

  abstract User fromDto(UserDto dto);

  abstract List<User> fromDto(List<UserBaseDto> userDtoList);

  abstract UserDto toDto(User user);

  @Mapping(target = "password", ignore = true)
  abstract User fromBaseDto(UserBaseDto dto);

  abstract UserBaseDto toBaseDto(User user);

  abstract UserUpdateDto toUpdateDto(User user);

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "login", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "email", ignore = true)
  @Mapping(target = "authority", ignore = true)
  abstract User fromUpdateDto(UserUpdateDto userUpdateDto);

  abstract UserSecurityDto toSecurityDto(User user);

  @Mapping(target = "middleName", ignore = true)
  @Mapping(target = "lastName", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "firstName", ignore = true)
  @Mapping(target = "birthDate", ignore = true)
  @Mapping(target = "authority", ignore = true)
  abstract User fromSecurityDto(UserSecurityDto userSecurityDto);

  abstract List<UserBaseDto> toDto(List<User> users);
}
