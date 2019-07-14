package ru.relex.hotelteam.service.mapstruct;


import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.service.dto.UserSafeDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapstruct {

    User fromDTO(UserSafeDTO dto);

    UserSafeDTO toDTO(User user);

    UserUpdateDTO toUserUpdateDTO(User user);

    User fromUserUpdateDTO(UserUpdateDTO userUpdateDTO);

    List<UserSafeDTO> toDTO(List<User> users);

    List<User> fromDTO(List<UserSafeDTO> userSafeDTOS);
}
