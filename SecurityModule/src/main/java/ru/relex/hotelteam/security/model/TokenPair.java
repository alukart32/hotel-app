package ru.relex.hotelteam.security.model;

public class TokenPair {

  private String authToken;
  private String refreshToken;

  public TokenPair(String authToken, String refreshToken) {
    this.authToken = authToken;
    this.refreshToken = refreshToken;
  }

  public TokenPair() {
  }

  public String getAuthToken() {
    return authToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }
}
