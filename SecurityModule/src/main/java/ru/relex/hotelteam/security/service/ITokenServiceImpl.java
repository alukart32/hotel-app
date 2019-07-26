package ru.relex.hotelteam.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ITokenServiceImpl implements ITokenService {

  private static final Key SIGNING_KEY;
  private static final Integer EXPIRATION_TIME = 15;

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
  public String generateToken(final UserDetails user) {
    var now = Instant.now();
    Date dateId = Date.from(now);

    var expiration = now.plus(Duration.ofMinutes(EXPIRATION_TIME));
    Date expirationDate = Date.from(expiration);

    String authority = user.getAuthorities().stream().findFirst().orElseThrow( () -> new BadCredentialsException("Can`t get authorities for user " + user.getUsername())).getAuthority();
    return Jwts
        .builder()
        .signWith(SIGNING_KEY)
        .setSubject(user.getUsername())
        .claim("authority", authority)
        .setIssuedAt(dateId)
        .setExpiration(expirationDate)
        .compact();
  }
}
