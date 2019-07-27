package ru.relex.hotelteam.security.mapstruct;

import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.relex.hotelteam.shared.model.Authority;

@Mapper(componentModel = "spring")
public interface IAuthorityMapstruct {

  default GrantedAuthority toGrantedAuthority(Authority authority) {
    return authority != null ?
        new SimpleGrantedAuthority("ROLE_" + authority.name())
        : null;
  }

}
