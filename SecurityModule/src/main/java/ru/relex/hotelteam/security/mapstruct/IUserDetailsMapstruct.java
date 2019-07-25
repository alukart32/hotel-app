package ru.relex.hotelteam.security.mapstruct;

import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.security.model.AuthUserDetails;

@Mapper
public interface IUserDetailsMapstruct {
  default UserDetails mapUser(User user) {
    return new AuthUserDetails(user.getLogin(),
        user.getPassword(),
        user.getAuthority());
  }
}
