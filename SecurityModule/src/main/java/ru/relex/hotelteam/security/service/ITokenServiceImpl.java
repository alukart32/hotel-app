package ru.relex.hotelteam.security.service;

import static ru.relex.hotelteam.security.utils.SecurityConstraints.AUTH_EXPIRATION_TIME;
import static ru.relex.hotelteam.security.utils.SecurityConstraints.REFRESH_EXPIRATION_TIME;
import static ru.relex.hotelteam.security.utils.SecurityConstraints.SIGNING_KEY;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.util.Date;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.security.model.TokenPair;

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
    var now = Instant.now();

    Date dateId = Date.from(now);

    var authExpiration = now.plus(AUTH_EXPIRATION_TIME);
    Date authExpirationDate = Date.from(authExpiration);

    var authToken = Jwts
        .builder()
        .signWith(SIGNING_KEY)
        .setSubject(username)
        .setIssuedAt(dateId)
        .setExpiration(authExpirationDate)
        .compact();

    var refreshExpiration = now.plus(REFRESH_EXPIRATION_TIME);
    Date refreshExpirationDate = Date.from(refreshExpiration);

    var refreshToken = Jwts
        .builder()
        .signWith(SIGNING_KEY)
        .setSubject(username)
        .claim("refresh", "true")
        .setIssuedAt(dateId)
        .setExpiration(refreshExpirationDate)
        .compact();

    return new TokenPair(authToken, refreshToken);
  }
}
