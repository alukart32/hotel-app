package ru.relex.hotelteam.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface ITokenService {

  Jws<Claims> parseJws(String jwtToken);

  String generateToken(final String username);
}
