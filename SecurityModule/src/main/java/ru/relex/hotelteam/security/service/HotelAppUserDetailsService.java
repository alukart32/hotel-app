package ru.relex.hotelteam.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.security.mapstruct.IUserDetailsMapstruct;


/**
 * Используется чтобы сообщить менеджеру аутентификации, что за пользователь пришёл на авторизацию.
 */


@Service
public class HotelAppUserDetailsService implements UserDetailsService {

  private final IUserMapper userMapper;
  private final IUserDetailsMapstruct mapstruct;

  public HotelAppUserDetailsService(final IUserMapper userMapper, final IUserDetailsMapstruct mapstruct) {
    this.userMapper = userMapper;
    this.mapstruct = mapstruct;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    /**
     * username = login !!!
     */
    return userMapper
        .getUserByLogin(username)
        .map(mapstruct::mapUser)
        .orElseThrow(
            () -> new UsernameNotFoundException(username + " not found"));
  }

}
