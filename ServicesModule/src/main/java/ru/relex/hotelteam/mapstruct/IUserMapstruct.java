package ru.relex.hotelteam.mapstruct;


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.dto.UserBaseDto;
import ru.relex.hotelteam.dto.UserDto;
import ru.relex.hotelteam.dto.UserSecurityDto;
import ru.relex.hotelteam.dto.UserUpdateDto;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

  User fromDto(UserDto dto);

  UserDto toDto(User user);

  User fromBaseDto(UserBaseDto dto);

  UserBaseDto toBaseDto(User user);

  UserUpdateDto toUpdateDto(User user);

  User fromUpdateDto(UserUpdateDto userUpdateDto);

  UserSecurityDto toSecurityDto(User user);

  User fromSecurityDto(UserSecurityDto userSecurityDto);

  List<UserBaseDto> toDto(List<User> users);

  List<User> fromDto(List<UserBaseDto> userDtoList);
}
