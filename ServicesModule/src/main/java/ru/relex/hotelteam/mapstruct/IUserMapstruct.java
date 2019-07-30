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

  abstract List<User> fromDto(List<UserBaseDto> userDtoList);

  abstract UserDto toDto(User user);

  @Mapping(target = "password", expression = "java(mapPassword(dto))")
  @Mapping(target = "authority", ignore = true)
  public abstract User toDomain(UserDto dto);

  public abstract List<UserBaseDto> fromDomain(List<User> users);

  public abstract List<User> toDomain(List<UserDto> userDtos);

  @Mapping(target = "password", ignore = true)
  public abstract User fromBaseDto(UserBaseDto dto);

  public abstract UserBaseDto toBaseDto(User user);

  public abstract UserUpdateDto toUpdateDto(User user);

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "login", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "email", ignore = true)
  @Mapping(target = "authority", ignore = true)
  public abstract User fromUpdateDto(UserUpdateDto userUpdateDto);

  public abstract UserSecurityDto toSecurityDto(User user);

  @Mapping(target = "middleName", ignore = true)
  @Mapping(target = "lastName", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "firstName", ignore = true)
  @Mapping(target = "birthDate", ignore = true)
  @Mapping(target = "authority", ignore = true)
  public abstract User fromSecurityDto(UserSecurityDto userSecurityDto);

}
