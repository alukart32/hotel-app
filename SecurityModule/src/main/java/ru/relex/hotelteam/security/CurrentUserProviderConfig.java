package ru.relex.hotelteam.security;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.shared.model.CurrentUser;

@Configuration
public class CurrentUserProviderConfig {

  @Bean
  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
  CurrentUser getCurrentUser(final IUserMapper mapper) {
    Object principal = SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();
    if (principal == null) {
      throw new BadCredentialsException("Can`t get Current user. Authentication principal is null");
    }
    if (!(principal instanceof String)) {
      throw new IllegalArgumentException(
          String.format("Expected String as principal, but got [%s]",
              principal.getClass().getName()));
    }
    Optional<CurrentUser> user = mapper.getCurrentUser((String) principal);
    return user.orElseThrow(() ->
        new BadCredentialsException(
            String.format("Can`t found user with username [%s]", (String) principal)
        ));
  }

}
