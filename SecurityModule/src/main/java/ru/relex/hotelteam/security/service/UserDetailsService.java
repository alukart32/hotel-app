package ru.relex.hotelteam.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.mapper.IUserMapper;

/**
 * Используется чтобы сообщить менеджеру аутентификации, что за пользователь пришёл на авторизацию.
 */
@Service
public class UserDetailsService {

  private final IUserMapper userMapper;
  private final IUserDetailsMapstruct mapstruct;

  public ShowcaseUserDetailsService(final IUserMapper userMapper, final IUserDetailsMapstruct mapstruct) {
    this.userMapper = userMapper;
    this.mapstruct = mapstruct;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return userMapper
        .getUserByUsername(username)
        .map(mapstruct::mapUser)
        .orElseThrow(
            () -> new UsernameNotFoundException(username + " not found"));
  }
}
