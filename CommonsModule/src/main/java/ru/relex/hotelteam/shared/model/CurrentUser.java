package ru.relex.hotelteam.shared.model;

public class CurrentUser {

  private final int id;
  private final String login;
  private final Authority authority;

  public CurrentUser(int id, String login, Authority authority) {
    this.id = id;
    this.login = login;
    this.authority = authority;
  }

  public int getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public Authority getAuthority() {
    return authority;
  }
}
