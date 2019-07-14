package ru.relex.hotelteam.service.mapstruct;


import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.service.dto.UserSafeDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

    User fromSafeDTO(UserSafeDTO dto);

    UserSafeDTO toSafeDTO(User user);

    UserUpdateDTO toUpdateDTO(User user);

    User fromUpdateDTO(UserUpdateDTO userUpdateDTO);

    List<UserSafeDTO> toSafeDTOs(List<User> users);

    List<User> fromDTO(List<UserSafeDTO> userSafeDTOS);
}
