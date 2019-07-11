package ru.relex.hotelteam.service.mapstruct;


import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.service.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

    User fromDto(UserDTO dto);

    UserDTO toDto(User user);

    List<UserDTO> toDto(List<User> users);

    List<User> fromDto(List<UserDTO> userDTOs);
}
