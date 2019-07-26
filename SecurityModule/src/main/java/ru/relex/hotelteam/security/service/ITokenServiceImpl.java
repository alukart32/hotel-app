package ru.relex.hotelteam.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.stereotype.Service;

@Service
public class ITokenServiceImpl implements ITokenService {

  private static final Key SIGNING_KEY;

  // Этот ключ генерируется каждый раз при запуске приложения, что значит что токены после перезапуска
  // будут невалидными.
  static {
//    final SecureRandom secureRandom = new SecureRandom();
//
//    final byte[] bytes = new byte[512 / 8];
//    secureRandom.nextBytes(bytes);

    String SECRET = "д!lаїiЎ\n"
        + "#mщ\u007Fа\n"
        + "rЩс¦1'іMЮ1=7рgя\u0013\u0014ћdaеiCµG\u0006МРa\\Ж:“»YўnW¤\u001DЗ»DЛІ>¦\u0003м{";

    SIGNING_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
  }

  @Override
  public Jws<Claims> parseJws(String jwsToken) {
    return Jwts
        .parser()
        .setSigningKey(SIGNING_KEY)
        .parseClaimsJws(jwsToken);
  }

  @Override
  public String generateToken(final String username) {
    return Jwts
        .builder()
        .signWith(SIGNING_KEY)
        .setSubject(username)
        .compact();
  }
}
