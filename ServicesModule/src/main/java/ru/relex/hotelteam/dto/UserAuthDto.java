package ru.relex.hotelteam.dto;

import ru.relex.hotelteam.shared.model.Authority;

/**
 * Created by Tarasov Ivan.
 */
public class UserAuthDto {

  Authority authority;

  public Authority getAuthority() {
    return authority;
  }

  public void setAuthority(Authority authority) {
    this.authority = authority;
  }
}
