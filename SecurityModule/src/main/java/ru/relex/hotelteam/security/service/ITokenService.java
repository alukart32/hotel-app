package ru.relex.hotelteam.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import ru.relex.hotelteam.security.model.TokenPair;

public interface ITokenService {

  Jws<Claims> parseJws(String jwtToken);

  TokenPair generateToken(final String username);
}
