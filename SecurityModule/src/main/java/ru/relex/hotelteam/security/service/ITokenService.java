package ru.relex.hotelteam.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITokenService {

  Jws<Claims> parseJws(String jwtToken);

  String generateToken(final UserDetails user);
}
