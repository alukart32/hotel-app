package ru.relex.hotelteam.security.utils;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.security.SecureRandom;
import java.time.Duration;

public class SecurityConstraints {

  public static final String LOGIN_API = "/login/";
  public static final String REGISTRATION_API = "/users/";
  public static final String REFRESH_API = "/refresh/";
  public static final String AUTH_HEADER = "Authorization";
  public static final String BEARER = "Bearer ";


  public static final Duration REFRESH_EXPIRATION_TIME = Duration.ofDays(1);
  public static final Duration AUTH_EXPIRATION_TIME = Duration.ofMinutes(5);
  public static final Key SIGNING_KEY;
  private static final Boolean KEY_GENERATION = false;

  static {
    String SECRET;
    if (KEY_GENERATION) {
      final SecureRandom secureRandom = new SecureRandom();
      final byte[] bytes = new byte[512 / 8];
      secureRandom.nextBytes(bytes);
      SECRET = new String(bytes);
    }
    SECRET = "д!lаїiЎ\n"
        + "#mщ\u007Fа\n"
        + "rЩс¦1'іMЮ1=7рgя\u0013\u0014ћdaеiCµG\u0006МРa\\Ж:“»YўnW¤\u001DЗ»DЛІ>¦\u0003м{";

    SIGNING_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
  }
}
