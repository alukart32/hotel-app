package ru.relex.hotelteam.service.mapstruct;


import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.service.dto.UserDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

    User fromSafeDTO(UserDTO dto);

    UserDTO toSafeDTO(User user);

    UserUpdateDTO toUpdateDTO(User user);

    User fromUpdateDTO(UserUpdateDTO userUpdateDTO);

    List<UserDTO> toSafeDTOs(List<User> users);

    List<User> fromDTO(List<UserDTO> userSafeDTOS);
}
