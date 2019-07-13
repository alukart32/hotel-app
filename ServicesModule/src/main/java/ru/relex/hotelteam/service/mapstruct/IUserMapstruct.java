package ru.relex.hotelteam.service.mapstruct;


import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.service.dto.UserDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

    User fromDTO(UserDTO dto);

    UserDTO toDTO(User user);

    UserUpdateDTO toUserUpdateDTO(User user);

    User fromUserUpdateDTO(UserUpdateDTO userUpdateDTO);

    List<UserDTO> toDTO(List<User> users);

    List<User> fromDTO(List<UserDTO> userDTOs);
}
