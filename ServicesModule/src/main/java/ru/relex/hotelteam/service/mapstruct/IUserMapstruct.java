package ru.relex.hotelteam.service.mapstruct;


import java.util.List;
import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.service.dto.UserDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

  User fromDTO(UserDTO dto);

  UserDTO toDTO(User user);

  UserUpdateDTO toUpdateDTO(User user);

  User fromUpdateDTO(UserUpdateDTO userUpdateDTO);

  List<UserDTO> toDTO(List<User> users);

  List<User> fromDTO(List<UserDTO> userSafeDTOS);
}
