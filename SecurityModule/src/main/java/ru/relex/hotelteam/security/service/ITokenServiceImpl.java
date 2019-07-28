package ru.relex.hotelteam.security.service;

import static ru.relex.hotelteam.security.utils.SecurityConstraints.AUTH_EXPIRATION_TIME;
import static ru.relex.hotelteam.security.utils.SecurityConstraints.REFRESH_EXPIRATION_TIME;
import static ru.relex.hotelteam.security.utils.SecurityConstraints.SIGNING_KEY;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.security.model.TokenPair;
import ru.relex.hotelteam.shared.model.CurrentUser;

@Service
public class ITokenServiceImpl implements ITokenService {

  private IUserMapper mapper;

  public ITokenServiceImpl(IUserMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public Jws<Claims> parseJws(String jwsToken) {
    return Jwts
        .parser()
        .setSigningKey(SIGNING_KEY)
        .parseClaimsJws(jwsToken);
  }

  @Override
  public TokenPair generateToken(final String username) {
    Instant now = Instant.now();

    Optional<CurrentUser> userOptional = mapper.getCurrentUser(username);

    CurrentUser user = userOptional.orElseThrow(
        () -> new BadCredentialsException(
            String.format("Can`t find user with login [%s]", username)
        ));
    String authToken = generateAuthToken(user, now);
    String refreshToken = generateRefreshToken(user, now);

    return new TokenPair(authToken, refreshToken);
  }

  private String generateRefreshToken(CurrentUser user, Instant now) {
    Date dateId = Date.from(now);

    var refreshExpiration = now.plus(REFRESH_EXPIRATION_TIME);
    Date refreshExpirationDate = Date.from(refreshExpiration);

    return Jwts
        .builder()
        .signWith(SIGNING_KEY)
        .setSubject(user.getLogin())
        .claim("refresh", "true")
        .setIssuedAt(dateId)
        .setExpiration(refreshExpirationDate)
        .compact();
  }

  private String generateAuthToken(CurrentUser user, Instant now) {
    Date dateId = Date.from(now);

    var authExpiration = now.plus(AUTH_EXPIRATION_TIME);
    Date authExpirationDate = Date.from(authExpiration);

    return Jwts
        .builder()
        .signWith(SIGNING_KEY)
        .setSubject(user.getLogin())
        .claim("id", user.getId())
        .claim("authority", user.getAuthority().name())
        .setIssuedAt(dateId)
        .setExpiration(authExpirationDate)
        .compact();
  }
}
