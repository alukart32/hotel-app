package ru.relex.hotelteam.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.security.model.TokenPair;

/**
 * Вызывается когда пользователь успешно авторизуется через страницу логина.
 */
@Service
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final ITokenService tokenService;
  private final ObjectMapper mapper;

  public JwtAuthenticationSuccessHandler(ITokenService tokenService, ObjectMapper mapper) {
    this.tokenService = tokenService;
    this.mapper = mapper;
  }

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request,
      final HttpServletResponse response,
      final Authentication authentication) throws IOException, ServletException {
    Object principal = authentication.getPrincipal(); // сюда приходят UserDetails после логина
    if (principal instanceof UserDetails) {
      TokenPair tokens = tokenService.generateToken(((UserDetails) principal).getUsername());
      try (var writer = response.getWriter()) {
        mapper.writeValue(writer, tokens);
      }
      response.setStatus(HttpServletResponse.SC_OK);
    }
  }

}
