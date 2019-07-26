package ru.relex.hotelteam.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * Вызывается когда пользователь успешно авторизуется через страницу логина.
 */
@Service
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private static final String BEARER = "Bearer ";
  private static final String AUTHORIZATION = "Authorization";
  private static final int ACCESS_TIME = 5;
  private static final int REFRESH_TIME = 30;

  private final ITokenService tokenService;

  public JwtAuthenticationSuccessHandler(final ITokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request,
      final HttpServletResponse response,
      final Authentication authentication) throws IOException, ServletException {
    Object principal = authentication.getPrincipal(); // сюда приходят UserDetails после логина
    if (principal instanceof UserDetails) {
      String token = tokenService.generateToken(((UserDetails)principal).getUsername());
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().println(token);
    }
  }

}
