package ru.relex.hotelteam.service.mapstruct;


import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.service.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

    User fromDTO(UserDTO dto);

    UserDTO toDTO(User user);

    List<UserDTO> toDTO(List<User> users);

    List<User> fromDTO(List<UserDTO> userDTOs);
}
