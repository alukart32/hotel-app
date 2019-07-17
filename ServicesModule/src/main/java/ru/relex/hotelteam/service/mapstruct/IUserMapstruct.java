package ru.relex.hotelteam.service.mapstruct;


import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.service.dto.UserDto;
import ru.relex.hotelteam.service.dto.UserUpdateDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

  User fromDTO(UserDto dto);

  UserDto toDTO(User user);

  UserUpdateDto toUpdateDto(User user);

  User fromUpdateDto(UserUpdateDto userUpdateDTO);

  List<UserDto> toDTO(List<User> users);

  List<User> fromDTO(List<UserDto> userDtoList);
}
